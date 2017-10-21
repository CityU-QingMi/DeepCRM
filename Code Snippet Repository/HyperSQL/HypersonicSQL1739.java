    @SuppressWarnings("")
    protected <T extends Result>T createStAXResult(
            Class<T> resultClass) throws SQLException {

        StAXResult result = null;

        try {
            this.domResult =
                new DOMResult((new SAX2DOMBuilder()).getDocument());

            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter xmlStreamWriter =
                factory.createXMLStreamWriter(this.domResult);

            if (resultClass == null || resultClass == StAXResult.class) {
                result = new StAXResult(xmlStreamWriter);
            } else {
                Constructor ctor =
                    resultClass.getConstructor(XMLStreamWriter.class);

                result = (StAXResult) ctor.newInstance(xmlStreamWriter);
            }
        } catch (ParserConfigurationException ex) {
            throw Exceptions.resultInstantiation(ex);
        } catch (SecurityException ex) {
            throw Exceptions.resultInstantiation(ex);
        } catch (IllegalArgumentException ex) {
            throw Exceptions.resultInstantiation(ex);
        } catch (IllegalAccessException ex) {
            throw Exceptions.resultInstantiation(ex);
        } catch (InvocationTargetException ex) {
            throw Exceptions.resultInstantiation(ex.getTargetException());
        } catch (FactoryConfigurationError ex) {
            throw Exceptions.resultInstantiation(ex);
        } catch (InstantiationException ex) {
            throw Exceptions.resultInstantiation(ex);
        } catch (NoSuchMethodException ex) {
            throw Exceptions.resultInstantiation(ex);
        } catch (XMLStreamException ex) {
            throw Exceptions.resultInstantiation(ex);
        }

        return (T) result;
    }
