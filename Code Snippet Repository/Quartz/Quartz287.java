    @Override           
    protected Object getObjectFromBlob(ResultSet rs, String colName)
        throws ClassNotFoundException, IOException, SQLException {
        InputStream binaryInput = rs.getBinaryStream(colName);

        if(binaryInput == null || binaryInput.available() == 0) {
            return null;
        }
        
        Object obj = null;
        
        ObjectInputStream in = new ObjectInputStream(binaryInput);
        try {
            obj = in.readObject();
        } finally {
            in.close();
        }

        return obj;
    }
