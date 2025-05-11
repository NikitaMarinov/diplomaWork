#!/bin/bash

echo "? Waiting Elasticsearch..."
until curl -s http://elasticsearch:9200/_cluster/health?wait_for_status=yellow; do
  sleep 2
done

echo "? Creating index - order_index..."
curl -X PUT "http://elasticsearch:9200/order_index" -H 'Content-Type: application/json' -d '
{
  "mappings": {
    "properties": {
      "id": { "type": "long" },
      "productId": { "type": "long" },
      "name": { "type": "text" },
      "brand": { "type": "text" },
      "model": { "type": "text" },
      "pricePerObject": { "type": "long" },
      "locationId": { "type": "long" },
      "price": { "type": "long" },
      "quantity": { "type": "integer" },
      "orderDate": { "type": "date", "format": "yyyy-MM-dd" },
      "customerName": { "type": "text" },
      "status": { "type": "keyword" }
    }
  }
}'

echo "? Creating index - manufacture_index..."
curl -X PUT "http://elasticsearch:9200/manufacture_index" -H 'Content-Type: application/json' -d '
{
  "mappings": {
    "properties": {
      "id": { "type": "long" },
      "productId": { "type": "long" },
      "name": { "type": "text" },
      "brand": { "type": "text" },
      "model": { "type": "text" },
      "quantity": { "type": "integer" },
      "status": { "type": "keyword" },
      "productionTime": { "type": "text" },
      "locationId": { "type": "long" }
    }
  }
}'

echo "? Creating index - logistics_index..."
curl -X PUT "http://elasticsearch:9200/logistics_index" -H 'Content-Type: application/json' -d '
{
  "mappings": {
    "properties": {
      "id": { "type": "long" },
      "productId": { "type": "long" },
      "name": { "type": "text" },
      "brand": { "type": "text" },
      "model": { "type": "text" },
      "locationId": { "type": "long" },
      "city": { "type": "text" },
      "country": { "type": "text" },
      "distanceToWarehouse": { "type": "long" },
      "transportId": { "type": "long" },
      "carType": { "type": "keyword" },
      "speed": { "type": "long" },
      "loadVolume": { "type": "long" },
      "quantity": { "type": "integer" },
      "customerName": { "type": "text" },
      "status": { "type": "keyword" },
      "deliveryDuration": { "type": "text" }
    }
  }
}'

echo "? Creating index - sales_index..."
curl -X PUT "http://elasticsearch:9200/sales_index" -H 'Content-Type: application/json' -d '
{
  "mappings": {
    "properties": {
      "id": { "type": "long" },
      "productId": { "type": "long" },
      "name": { "type": "text" },
      "brand": { "type": "text" },
      "model": { "type": "text" },
      "locationId": { "type": "long" },
      "city": { "type": "text" },
      "country": { "type": "text" },
      "price": { "type": "long" },
      "quantity": { "type": "integer" },
      "orderDate": { "type": "date" },
      "status": { "type": "keyword" }
    }
  }
}'

echo "? All indexes created."


curl -X POST "http://kibana:5601/api/index_patterns/index_pattern" -H 'kbn-xsrf: true' -H 'Content-Type: application/json' -d '
{
  "index_pattern": {
    "title": "order_index",
    "timeFieldName": "orderDate"
  }
}'

curl -X POST "http://kibana:5601/api/index_patterns/index_pattern" -H 'kbn-xsrf: true' -H 'Content-Type: application/json' -d '
{
  "index_pattern": {
    "title": "manufacture_index"
  }
}'

curl -X POST "http://kibana:5601/api/index_patterns/index_pattern" -H 'kbn-xsrf: true' -H 'Content-Type: application/json' -d '
{
  "index_pattern": {
    "title": "logistics_index"
  }
}'

curl -X POST "http://kibana:5601/api/index_patterns/index_pattern" -H 'kbn-xsrf: true' -H 'Content-Type: application/json' -d '
{
  "index_pattern": {
    "title": "sales_index",
    "timeFieldName": "orderDate"
  }
}'
