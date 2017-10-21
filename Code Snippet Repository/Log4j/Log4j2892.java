    private void writeObject(final ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        try {
            forEach(SERIALIZER, s);
        } catch (final RuntimeException runex) {
            if (runex.getCause() instanceof IOException) {
                throw (IOException) runex.getCause();
            }
            throw runex;
        }
    }
