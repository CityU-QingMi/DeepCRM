    @SuppressWarnings("")
    public static <T> T deserialize(final byte[] data) {
        try {
            final ByteArrayInputStream bas = new ByteArrayInputStream(data);
            final ObjectInputStream ois = new ObjectInputStream(bas);
            return (T) ois.readObject();
        } catch (final Exception ex) {
            throw new IllegalStateException("Could not deserialize", ex);
        }
    }
