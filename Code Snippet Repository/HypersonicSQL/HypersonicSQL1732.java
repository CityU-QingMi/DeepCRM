    @SuppressWarnings("")
    protected <T extends Source>T createDOMSource(
            Class<T> sourceClass) throws SQLException {

        DOMSource source = null;

        try {
            source = (sourceClass == null) ? new DOMSource()
                    : (DOMSource) sourceClass.newInstance();
        } catch (SecurityException ex) {
            throw Exceptions.sourceInstantiation(ex);
        } catch (IllegalAccessException ex) {
            throw Exceptions.sourceInstantiation(ex);
        } catch (InstantiationException ex) {
            throw Exceptions.sourceInstantiation(ex);
        } catch (ClassCastException ex) {
            throw Exceptions.sourceInstantiation(ex);
        }

        Transformer  transformer  = JDBCSQLXML.getIdentityTransformer();
        InputStream  stream       = this.getBinaryStreamImpl();
        StreamSource streamSource = new StreamSource();
        DOMResult    result       = new DOMResult();

        streamSource.setInputStream(stream);

        try {
            transformer.transform(streamSource, result);
        } catch (TransformerException ex) {
            throw Exceptions.transformFailed(ex);
        }
        source.setNode(result.getNode());
        source.setSystemId(result.getSystemId());

        return (T) source;
    }
