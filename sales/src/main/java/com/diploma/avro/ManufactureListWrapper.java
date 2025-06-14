/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.diploma.avro;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class ManufactureListWrapper extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -6814193554381969460L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"ManufactureListWrapper\",\"namespace\":\"com.diploma.avro\",\"fields\":[{\"name\":\"manufactures\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"ManufactureDTO\",\"fields\":[{\"name\":\"id\",\"type\":[\"null\",\"long\"],\"default\":null}]}}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<ManufactureListWrapper> ENCODER =
      new BinaryMessageEncoder<ManufactureListWrapper>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<ManufactureListWrapper> DECODER =
      new BinaryMessageDecoder<ManufactureListWrapper>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<ManufactureListWrapper> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<ManufactureListWrapper> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<ManufactureListWrapper> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<ManufactureListWrapper>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this ManufactureListWrapper to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a ManufactureListWrapper from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a ManufactureListWrapper instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static ManufactureListWrapper fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private java.util.List<com.diploma.avro.ManufactureDTO> manufactures;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public ManufactureListWrapper() {}

  /**
   * All-args constructor.
   * @param manufactures The new value for manufactures
   */
  public ManufactureListWrapper(java.util.List<com.diploma.avro.ManufactureDTO> manufactures) {
    this.manufactures = manufactures;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return manufactures;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: manufactures = (java.util.List<com.diploma.avro.ManufactureDTO>)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'manufactures' field.
   * @return The value of the 'manufactures' field.
   */
  public java.util.List<com.diploma.avro.ManufactureDTO> getManufactures() {
    return manufactures;
  }


  /**
   * Sets the value of the 'manufactures' field.
   * @param value the value to set.
   */
  public void setManufactures(java.util.List<com.diploma.avro.ManufactureDTO> value) {
    this.manufactures = value;
  }

  /**
   * Creates a new ManufactureListWrapper RecordBuilder.
   * @return A new ManufactureListWrapper RecordBuilder
   */
  public static com.diploma.avro.ManufactureListWrapper.Builder newBuilder() {
    return new com.diploma.avro.ManufactureListWrapper.Builder();
  }

  /**
   * Creates a new ManufactureListWrapper RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new ManufactureListWrapper RecordBuilder
   */
  public static com.diploma.avro.ManufactureListWrapper.Builder newBuilder(com.diploma.avro.ManufactureListWrapper.Builder other) {
    if (other == null) {
      return new com.diploma.avro.ManufactureListWrapper.Builder();
    } else {
      return new com.diploma.avro.ManufactureListWrapper.Builder(other);
    }
  }

  /**
   * Creates a new ManufactureListWrapper RecordBuilder by copying an existing ManufactureListWrapper instance.
   * @param other The existing instance to copy.
   * @return A new ManufactureListWrapper RecordBuilder
   */
  public static com.diploma.avro.ManufactureListWrapper.Builder newBuilder(com.diploma.avro.ManufactureListWrapper other) {
    if (other == null) {
      return new com.diploma.avro.ManufactureListWrapper.Builder();
    } else {
      return new com.diploma.avro.ManufactureListWrapper.Builder(other);
    }
  }

  /**
   * RecordBuilder for ManufactureListWrapper instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<ManufactureListWrapper>
    implements org.apache.avro.data.RecordBuilder<ManufactureListWrapper> {

    private java.util.List<com.diploma.avro.ManufactureDTO> manufactures;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.diploma.avro.ManufactureListWrapper.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.manufactures)) {
        this.manufactures = data().deepCopy(fields()[0].schema(), other.manufactures);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
    }

    /**
     * Creates a Builder by copying an existing ManufactureListWrapper instance
     * @param other The existing instance to copy.
     */
    private Builder(com.diploma.avro.ManufactureListWrapper other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.manufactures)) {
        this.manufactures = data().deepCopy(fields()[0].schema(), other.manufactures);
        fieldSetFlags()[0] = true;
      }
    }

    /**
      * Gets the value of the 'manufactures' field.
      * @return The value.
      */
    public java.util.List<com.diploma.avro.ManufactureDTO> getManufactures() {
      return manufactures;
    }


    /**
      * Sets the value of the 'manufactures' field.
      * @param value The value of 'manufactures'.
      * @return This builder.
      */
    public com.diploma.avro.ManufactureListWrapper.Builder setManufactures(java.util.List<com.diploma.avro.ManufactureDTO> value) {
      validate(fields()[0], value);
      this.manufactures = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'manufactures' field has been set.
      * @return True if the 'manufactures' field has been set, false otherwise.
      */
    public boolean hasManufactures() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'manufactures' field.
      * @return This builder.
      */
    public com.diploma.avro.ManufactureListWrapper.Builder clearManufactures() {
      manufactures = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ManufactureListWrapper build() {
      try {
        ManufactureListWrapper record = new ManufactureListWrapper();
        record.manufactures = fieldSetFlags()[0] ? this.manufactures : (java.util.List<com.diploma.avro.ManufactureDTO>) defaultValue(fields()[0]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<ManufactureListWrapper>
    WRITER$ = (org.apache.avro.io.DatumWriter<ManufactureListWrapper>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<ManufactureListWrapper>
    READER$ = (org.apache.avro.io.DatumReader<ManufactureListWrapper>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    long size0 = this.manufactures.size();
    out.writeArrayStart();
    out.setItemCount(size0);
    long actualSize0 = 0;
    for (com.diploma.avro.ManufactureDTO e0: this.manufactures) {
      actualSize0++;
      out.startItem();
      e0.customEncode(out);
    }
    out.writeArrayEnd();
    if (actualSize0 != size0)
      throw new java.util.ConcurrentModificationException("Array-size written was " + size0 + ", but element count was " + actualSize0 + ".");

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      long size0 = in.readArrayStart();
      java.util.List<com.diploma.avro.ManufactureDTO> a0 = this.manufactures;
      if (a0 == null) {
        a0 = new SpecificData.Array<com.diploma.avro.ManufactureDTO>((int)size0, SCHEMA$.getField("manufactures").schema());
        this.manufactures = a0;
      } else a0.clear();
      SpecificData.Array<com.diploma.avro.ManufactureDTO> ga0 = (a0 instanceof SpecificData.Array ? (SpecificData.Array<com.diploma.avro.ManufactureDTO>)a0 : null);
      for ( ; 0 < size0; size0 = in.arrayNext()) {
        for ( ; size0 != 0; size0--) {
          com.diploma.avro.ManufactureDTO e0 = (ga0 != null ? ga0.peek() : null);
          if (e0 == null) {
            e0 = new com.diploma.avro.ManufactureDTO();
          }
          e0.customDecode(in);
          a0.add(e0);
        }
      }

    } else {
      for (int i = 0; i < 1; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          long size0 = in.readArrayStart();
          java.util.List<com.diploma.avro.ManufactureDTO> a0 = this.manufactures;
          if (a0 == null) {
            a0 = new SpecificData.Array<com.diploma.avro.ManufactureDTO>((int)size0, SCHEMA$.getField("manufactures").schema());
            this.manufactures = a0;
          } else a0.clear();
          SpecificData.Array<com.diploma.avro.ManufactureDTO> ga0 = (a0 instanceof SpecificData.Array ? (SpecificData.Array<com.diploma.avro.ManufactureDTO>)a0 : null);
          for ( ; 0 < size0; size0 = in.arrayNext()) {
            for ( ; size0 != 0; size0--) {
              com.diploma.avro.ManufactureDTO e0 = (ga0 != null ? ga0.peek() : null);
              if (e0 == null) {
                e0 = new com.diploma.avro.ManufactureDTO();
              }
              e0.customDecode(in);
              a0.add(e0);
            }
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










