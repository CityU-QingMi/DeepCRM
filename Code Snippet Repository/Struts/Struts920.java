    public void toBytes() {
        if (!byteC.isNull()) {
            type=T_BYTES;
            return;
        }
        toString();
        type=T_BYTES;
        Charset charset = byteC.getCharset();
        ByteBuffer result = charset.encode(strValue);
        byteC.setBytes(result.array(), result.arrayOffset(), result.limit());
    }
