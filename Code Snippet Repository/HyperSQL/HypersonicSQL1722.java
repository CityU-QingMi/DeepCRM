    protected void init(Source source) throws SQLException {

        if (source == null) {
            throw JDBCUtil.nullArgument("source");
        }

        Transformer           transformer =
            JDBCSQLXML.getIdentityTransformer();
        StreamResult          result      = new StreamResult();
        ByteArrayOutputStream baos        = new ByteArrayOutputStream();
        GZIPOutputStream      gzos;

        try {
            gzos = new GZIPOutputStream(baos);
        } catch (IOException ex) {
            throw Exceptions.transformFailed(ex);
        }
        result.setOutputStream(gzos);

        try {
            transformer.transform(source, result);
        } catch (TransformerException ex) {
            throw Exceptions.transformFailed(ex);
        }

        try {
            gzos.close();
        } catch (IOException ex) {
            throw Exceptions.transformFailed(ex);
        }

        byte[] data = baos.toByteArray();

        setGZipData(data);
        setReadable(true);
        setWritable(false);
    }
