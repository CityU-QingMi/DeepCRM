    protected void writeBit(BinaryData o) {

        ensureRoom((int) (o.length(null) * 8 + 2));
        write('\'');

        String s = StringConverter.byteArrayToBitString(o.getBytes(),
            (int) o.bitLength(null));

        writeBytes(s);
        write('\'');
    }
