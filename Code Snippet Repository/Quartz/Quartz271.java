    @Override
    protected Object getJobDataFromBlob(ResultSet rs, String colName) throws ClassNotFoundException, IOException, SQLException {
        if (canUseProperties()) {
            Blob blob = rs.getBlob(colName);
            if (blob == null) {
                return null;
            } else {
                return new BlobFreeingStream(blob, blob.getBinaryStream());
            }
        } else {
            return getObjectFromBlob(rs, colName);
        }
    }
