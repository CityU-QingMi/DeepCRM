    @SuppressWarnings("")
    protected <T extends Result>T createDOMResult(
            Class<T> resultClass) throws SQLException {

        try {
            T result = (resultClass == null) ? ((T) new DOMResult())
                    : resultClass.newInstance();

            this.domResult = (DOMResult) result;

            return result;
        } catch (SecurityException ex) {
            throw Exceptions.resultInstantiation(ex);
        } catch (InstantiationException ex) {
            throw Exceptions.resultInstantiation(ex);
        } catch (IllegalAccessException ex) {
            throw Exceptions.resultInstantiation(ex);
        } catch (ClassCastException ex) {
            throw Exceptions.resultInstantiation(ex);
        }
    }
