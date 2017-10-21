    public static Object serializeClone(final Object obj)
        throws IOException, ClassNotFoundException {
        final ByteArrayOutputStream memOut = new ByteArrayOutputStream();
        try (final ObjectOutputStream objOut = new ObjectOutputStream(memOut)) {
            objOut.writeObject(obj);
        }

        final ByteArrayInputStream src = new ByteArrayInputStream(memOut.toByteArray());
        final ObjectInputStream objIs = new ObjectInputStream(src);

        return objIs.readObject();
    }
