    @Override
    protected Object getObjectFromBlob(ResultSet rs, String colName)
        throws ClassNotFoundException, IOException, SQLException {
        
        Object obj = null;

        Blob blobLocator = rs.getBlob(colName);
        InputStream binaryInput = null;
        try {
            if (null != blobLocator && blobLocator.length() > 0) {
                binaryInput = blobLocator.getBinaryStream();
            }
        } catch (Exception ignore) {
        }

        if (null != binaryInput) {
            ObjectInputStream in = new ObjectInputStream(binaryInput);
            try {
                obj = in.readObject();
            } finally {
                in.close();
            }
        }

        return obj;
    }
