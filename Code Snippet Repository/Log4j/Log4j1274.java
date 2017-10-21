    @Override
    public byte[] toByteArray(final LogEvent event) {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (final ObjectOutputStream oos = new PrivateObjectOutputStream(baos)) {
            oos.writeObject(event);
            oos.reset();
        } catch (final IOException ioe) {
            LOGGER.error("Serialization of LogEvent failed.", ioe);
        }
        return baos.toByteArray();
    }
