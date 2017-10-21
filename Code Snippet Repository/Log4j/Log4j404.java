    public static byte[] serialize(final Serializable obj) {
        try {
            final ByteArrayOutputStream bas = new ByteArrayOutputStream(8192);
            final ObjectOutputStream oos = new ObjectOutputStream(bas);
            oos.writeObject(obj);
            oos.flush();
            return bas.toByteArray();
        } catch (final Exception ex) {
            throw new IllegalStateException("Could not serialize", ex);
        }
    }
