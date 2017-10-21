    @SuppressWarnings("")
    protected <T extends Result>T createSAXResult(
            Class<T> resultClass) throws SQLException {

        SAXResult result = null;

        try {
            result = (resultClass == null) ? new SAXResult()
                    : (SAXResult) resultClass.newInstance();
        } catch (SecurityException ex) {
            throw Exceptions.resultInstantiation(ex);
        } catch (InstantiationException ex) {
            throw Exceptions.resultInstantiation(ex);
        } catch (IllegalAccessException ex) {
            throw Exceptions.resultInstantiation(ex);
        } catch (ClassCastException ex) {
            throw Exceptions.resultInstantiation(ex);
        }

        SAX2DOMBuilder handler = null;

        try {
            handler = new SAX2DOMBuilder();
        } catch (ParserConfigurationException ex) {
            throw Exceptions.resultInstantiation(ex);
        }
        this.domResult = new DOMResult();

        result.setHandler(handler);
        this.domResult.setNode(handler.getDocument());

        return (T) result;
    }
