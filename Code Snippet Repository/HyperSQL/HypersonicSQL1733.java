    @SuppressWarnings("")
    protected <T extends Source>T createSAXSource(
            Class<T> sourceClass) throws SQLException {

        SAXSource source = null;

        try {
            source = (sourceClass == null) ? new SAXSource()
                    : (SAXSource) sourceClass.newInstance();
        } catch (SecurityException ex) {
            throw Exceptions.sourceInstantiation(ex);
        } catch (InstantiationException ex) {
            throw Exceptions.sourceInstantiation(ex);
        } catch (IllegalAccessException ex) {
            throw Exceptions.sourceInstantiation(ex);
        } catch (ClassCastException ex) {
            throw Exceptions.sourceInstantiation(ex);
        }

        Reader      reader      = getCharacterStreamImpl();
        InputSource inputSource = new InputSource(reader);

        source.setInputSource(inputSource);

        return (T) source;
    }
