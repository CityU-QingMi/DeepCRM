    protected <T extends Result>T createStreamResult(
            Class<T> resultClass) throws SQLException {

        StreamResult result = null;

        try {
            result = (resultClass == null) ? new StreamResult()
                    : (StreamResult) resultClass.newInstance();
        } catch (SecurityException ex) {
            throw Exceptions.resultInstantiation(ex);
        } catch (InstantiationException ex) {
            throw Exceptions.resultInstantiation(ex);
        } catch (IllegalAccessException ex) {
            throw Exceptions.resultInstantiation(ex);
        } catch (ClassCastException ex) {
            throw Exceptions.resultInstantiation(ex);
        }

        OutputStream stream = setBinaryStreamImpl();

        result.setOutputStream(stream);

        return (T) result;
    }
