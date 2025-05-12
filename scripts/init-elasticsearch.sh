#!/bin/bash

echo "? Waiting Elasticsearch..."
until curl -s http://elasticsearch:9200/_cluster/health | grep -q '"status":"green"\|"status":"yellow"'; do
  sleep 2
done

echo "? Waiting for Kibana..."
until curl -s http://kibana:5601/api/status | grep -q '"level":"available"'; do
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
      "brand": { "type": "keyword" },
      "model": { "type": "keyword" },
      "pricePerObject": { "type": "long" },
      "locationId": { "type": "long" },
      "price": { "type": "long" },
      "quantity": { "type": "integer" },
      "orderDate": {"type": "date", "format": "strict_date_optional_time||yyyy-MM-dd HH:mm:ss" },
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
      "brand": { "type": "keyword" },
      "model": { "type": "keyword" },
      "quantity": { "type": "integer" },
      "status": { "type": "keyword" },
      "productionTime": { "type": "keyword" },
      "locationId": { "type": "long" },
      "productionEndTime": {"type": "date", "format": "strict_date_optional_time||yyyy-MM-dd HH:mm:ss" }
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
      "brand": { "type": "keyword" },
      "model": { "type": "keyword" },
      "locationId": { "type": "long" },
      "location": {"type": "geo_point"},
      "city": { "type": "keyword" },
      "country": { "type": "keyword" },
      "distanceToWarehouse": { "type": "long" },
      "transportId": { "type": "long" },
      "carType": { "type": "keyword" },
      "speed": { "type": "long" },
      "loadVolume": { "type": "long" },
      "quantity": { "type": "integer" },
      "customerName": { "type": "text" },
      "deliveryEndTime": {"type": "date", "format": "strict_date_optional_time||yyyy-MM-dd HH:mm:ss" },
      "status": { "type": "keyword" },
      "deliveryDuration": { "type": "keyword" }
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
      "name": { "type": "keyword" },
      "brand": { "type": "keyword" },
      "model": { "type": "keyword" },
      "locationId": { "type": "long" },
      "city": { "type": "keyword" },
      "country": { "type": "keyword" },
      "price": { "type": "long" },
      "quantity": { "type": "integer" },
      "orderDate": {"type": "date", "format": "strict_date_optional_time||yyyy-MM-dd HH:mm:ss" },
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
    "title": "manufacture_index",
    "timeFieldName": "productionEndTime"
  }
}'

curl -X POST "http://kibana:5601/api/index_patterns/index_pattern" -H 'kbn-xsrf: true' -H 'Content-Type: application/json' -d '
{
  "index_pattern": {
    "title": "logistics_index",
    "timeFieldName": "deliveryEndTime"
  }
}'

curl -X POST "http://kibana:5601/api/index_patterns/index_pattern" -H 'kbn-xsrf: true' -H 'Content-Type: application/json' -d '
{
  "index_pattern": {
    "title": "sales_index",
    "timeFieldName": "orderDate"
  }
}'
