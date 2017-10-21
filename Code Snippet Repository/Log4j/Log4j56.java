    public static void assertSerializationEquals(
        final String witness, final Object obj, final int[] skip,
        final int endCompare) throws Exception {
        final ByteArrayOutputStream memOut = new ByteArrayOutputStream();
        try (final ObjectOutputStream objOut = new ObjectOutputStream(memOut)) {
            objOut.writeObject(obj);
        }

        assertStreamEquals(witness, memOut.toByteArray(), skip, endCompare);
    }
