    protected static TransformerFactory getTransformerFactory() throws SQLException {

        if (JDBCSQLXML.transformerFactory == null) {
            try {
                JDBCSQLXML.transformerFactory =
                    TransformerFactory.newInstance();
            } catch (TransformerFactoryConfigurationError ex) {
                throw Exceptions.transformFailed(ex);
            }
        }

        return JDBCSQLXML.transformerFactory;
    }
