    protected InputStream getBinaryStreamImpl() throws SQLException {

        try {
            byte[]               data = getGZipData();
            ByteArrayInputStream bais = new ByteArrayInputStream(data);

            return new GZIPInputStream(bais);
        } catch (IOException ex) {
            throw Exceptions.transformFailed(ex);
        }
    }
