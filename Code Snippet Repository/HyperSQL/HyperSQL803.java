    protected static DOMImplementation getDOMImplementation() throws SQLException {

        if (domImplementation == null) {
            domImplementation =
                getDOMImplementationRegistry().getDOMImplementation(
                    domFeatures);
        }

        if (domImplementation == null) {
            Exception ex = new RuntimeException("Not supported: "
                + domFeatures);

            throw Exceptions.domInstantiation(ex);
        }

        return domImplementation;
    }
