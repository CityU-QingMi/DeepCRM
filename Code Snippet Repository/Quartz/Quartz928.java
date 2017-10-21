    @Override
    protected Object getJobDataFromBlob(ResultSet rs, String colName)
        throws ClassNotFoundException, IOException, SQLException {
        
        if (canUseProperties()) {
            Blob blobLocator = rs.getBlob(colName);
            InputStream binaryInput = null;
            try {
                if (null != blobLocator && blobLocator.length() > 0) {
                    binaryInput = blobLocator.getBinaryStream();
                }
            } catch (Exception ignore) {
            }
            return binaryInput;
        }

        return getObjectFromBlob(rs, colName);
    }
