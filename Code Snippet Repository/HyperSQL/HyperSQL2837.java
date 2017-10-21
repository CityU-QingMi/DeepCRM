    public static UUID getJavaUUID(BinaryData data) {

        if (data == null) {
            return null;
        }

        byte[] bytes = data.getBytes();
        long   msb   = ArrayUtil.byteSequenceToLong(bytes, 0);
        long   lsb   = ArrayUtil.byteSequenceToLong(bytes, 8);

        return new UUID(msb, lsb);
    }
