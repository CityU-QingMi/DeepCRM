    @Override
    protected Object getObjectFromBlob(ResultSet rs, String colName)
            throws ClassNotFoundException, IOException, SQLException {

        Object obj = null;
        InputStream binaryInput;

        Blob blob = rs.getBlob(colName);
        byte[] bytes = blob.getBytes(1, (int) blob.length());

        if (bytes != null && bytes.length != 0) {
            binaryInput = new ByteArrayInputStream(bytes);

            ObjectInputStream in = new ObjectInputStream(binaryInput);
            try {
                obj = in.readObject();
            } finally {
                in.close();
            }
        }

        return obj;
    }
