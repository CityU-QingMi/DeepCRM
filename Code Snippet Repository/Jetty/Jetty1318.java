    public static void putTo(HttpField field, ByteBuffer bufferInFillMode)
    {
        if (field instanceof PreEncodedHttpField)
        {
            ((PreEncodedHttpField)field).putTo(bufferInFillMode,HttpVersion.HTTP_1_0);
        }
        else
        {
            HttpHeader header=field.getHeader();
            if (header!=null)
            {
                bufferInFillMode.put(header.getBytesColonSpace());
                putSanitisedValue(field.getValue(),bufferInFillMode);
            }
            else
            {
                putSanitisedName(field.getName(),bufferInFillMode);
                bufferInFillMode.put(__colon_space);
                putSanitisedValue(field.getValue(),bufferInFillMode);
            }

            BufferUtil.putCRLF(bufferInFillMode);
        }
    }
