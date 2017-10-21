    protected <T extends Source>T getSourceImpl(
            Class<T> sourceClass) throws SQLException {

        if (JAXBSource.class.isAssignableFrom(sourceClass)) {

            // Must go first presently, since JAXBSource extends SAXSource
            // (purely as an implementation detail) and it's not possible
            // to instantiate a valid JAXBSource with a Zero-Args
            // constructor(or any subclass thereof, due to the finality of
            // its private marshaller and context object attributes)
            // FALL THROUGH... will throw an exception
        } else if (StreamSource.class.isAssignableFrom(sourceClass)) {
            return createStreamSource(sourceClass);
        } else if ((sourceClass == null)
                   || DOMSource.class.isAssignableFrom(sourceClass)) {
            return createDOMSource(sourceClass);
        } else if (SAXSource.class.isAssignableFrom(sourceClass)) {
            return createSAXSource(sourceClass);
        } else if (StAXSource.class.isAssignableFrom(sourceClass)) {
            return createStAXSource(sourceClass);
        }

        throw JDBCUtil.invalidArgument("sourceClass: " + sourceClass);
    }
