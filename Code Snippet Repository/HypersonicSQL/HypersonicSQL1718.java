    protected static Transformer getIdentityTransformer() throws SQLException {

        if (JDBCSQLXML.identityTransformer == null) {
            try {
                JDBCSQLXML.identityTransformer =
                    getTransformerFactory().newTransformer();
            } catch (TransformerConfigurationException ex) {
                throw Exceptions.transformFailed(ex);
            }
        }

        return JDBCSQLXML.identityTransformer;
    }
