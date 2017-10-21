    public InputStream getBlobInputStream (ResultSet result, String columnName)
    throws SQLException
    {
        if (_dbName == null)
            throw new IllegalStateException ("DbAdaptor missing metadata");
        
        if (_dbName.startsWith("postgres"))
        {
            byte[] bytes = result.getBytes(columnName);
            return new ByteArrayInputStream(bytes);
        }

        Blob blob = result.getBlob(columnName);
        return blob.getBinaryStream();
    }
