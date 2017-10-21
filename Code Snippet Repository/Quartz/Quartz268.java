    @Override
    protected Object getJobDataFromBlob(ResultSet rs, String colName)
            throws ClassNotFoundException, IOException, SQLException {

        if (canUseProperties()) {
            InputStream binaryInput;

            Blob blob = rs.getBlob(colName);
            byte[] bytes = blob.getBytes(1, (int) blob.length());

            if (bytes == null || bytes.length == 0) {
                return null;
            }
            binaryInput = new ByteArrayInputStream(bytes);
            return binaryInput;
        }

        return getObjectFromBlob(rs, colName);
    }
