    protected static DOMImplementationRegistry getDOMImplementationRegistry() throws SQLException {

        if (domImplementationRegistry == null) {
            try {
                domImplementationRegistry =
                    DOMImplementationRegistry.newInstance();
            } catch (ClassCastException ex) {
                throw Exceptions.domInstantiation(ex);
            } catch (InstantiationException ex) {
                throw Exceptions.domInstantiation(ex);
            } catch (ClassNotFoundException ex) {
                throw Exceptions.domInstantiation(ex);
            } catch (IllegalAccessException ex) {
                throw Exceptions.domInstantiation(ex);
            }
        }

        return domImplementationRegistry;
    }
