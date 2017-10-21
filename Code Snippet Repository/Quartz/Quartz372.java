    @Override           
    protected Object getJobDataFromBlob(ResultSet rs, String colName)
        throws ClassNotFoundException, IOException, SQLException {
        //log.debug( "Getting Job details from blob in col " + colName );
        if (canUseProperties()) {
            byte data[] = rs.getBytes(colName);
            if(data == null) {
                return null;
            }
            InputStream binaryInput = new ByteArrayInputStream(data);
            return binaryInput;
        }

        return getObjectFromBlob(rs, colName);
    }
