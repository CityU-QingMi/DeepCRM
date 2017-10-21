    @Override           
    protected Object getObjectFromBlob(ResultSet rs, String colName)
        throws ClassNotFoundException, IOException, SQLException {
        //log.debug( "Getting blob from column: " + colName );
        Object obj = null;

        byte binaryData[] = rs.getBytes(colName);

        InputStream binaryInput = new ByteArrayInputStream(binaryData);

        if (null != binaryInput && binaryInput.available() != 0) {
            ObjectInputStream in = new ObjectInputStream(binaryInput);
            try {
                obj = in.readObject();
            } finally {
                in.close();
            }
        }

        return obj;
    }
