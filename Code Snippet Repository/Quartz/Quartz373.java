    @Override           
    protected Object getObjectFromBlob(ResultSet rs, String colName)
        throws ClassNotFoundException, IOException, SQLException {
        InputStream binaryInput = null;
        byte[] bytes = rs.getBytes(colName);
        
        Object obj = null;
        
        if(bytes != null && bytes.length != 0) {
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
