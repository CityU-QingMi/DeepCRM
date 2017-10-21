    BinaryData readSizedBinaryData() throws IOException {

        int len = readInt();

        try {
            return (len < 0) ? null
                             : new BinaryData((long) len, this);
        } catch (HsqlException he) {
            throw new IOException(he.getMessage());
        }
    }
