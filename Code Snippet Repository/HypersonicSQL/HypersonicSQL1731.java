    @SuppressWarnings("")
    protected <T extends Source>T createStreamSource(
            Class<T> sourceClass) throws SQLException {

        StreamSource source = null;

        try {
            source = (sourceClass == null) ? new StreamSource()
                    : (StreamSource) sourceClass.newInstance();
        } catch (SecurityException ex) {
            throw Exceptions.sourceInstantiation(ex);
        } catch (InstantiationException ex) {
            throw Exceptions.sourceInstantiation(ex);
        } catch (IllegalAccessException ex) {
            throw Exceptions.sourceInstantiation(ex);
        } catch (ClassCastException ex) {
            throw Exceptions.sourceInstantiation(ex);
        }

        Reader reader = getCharacterStreamImpl();

        source.setReader(reader);

        return (T) source;
    }
