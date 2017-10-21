    protected <T extends Result>T createResult(
            Class<T> resultClass) throws SQLException {

        checkWritable();
        setWritable(false);
        setReadable(true);

        if (JAXBResult.class.isAssignableFrom(resultClass)) {

            // Must go first presently, since JAXBResult extends SAXResult
            // (purely as an implementation detail) and it's not possible
            // to instantiate a valid JAXBResult with a Zero-Args
            // constructor(or any subclass thereof, due to the finality of
            // its private UnmarshallerHandler)
            // FALL THROUGH... will throw an exception
        } else if ((resultClass == null)
                   || StreamResult.class.isAssignableFrom(resultClass)) {
            return createStreamResult(resultClass);
        } else if (DOMResult.class.isAssignableFrom(resultClass)) {
            return createDOMResult(resultClass);
        } else if (SAXResult.class.isAssignableFrom(resultClass)) {
            return createSAXResult(resultClass);
        } else if (StAXResult.class.isAssignableFrom(resultClass)) {
            return createStAXResult(resultClass);
        }

        throw JDBCUtil.invalidArgument("resultClass: " + resultClass);
    }
