    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        final StringFormattedMessage expected = new StringFormattedMessage("Msg", "a", "b", "c");
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (final ObjectOutputStream out = new ObjectOutputStream(baos)) {
            out.writeObject(expected);
        }
        final ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        final ObjectInputStream in = new ObjectInputStream(bais);
        final StringFormattedMessage actual = (StringFormattedMessage) in.readObject();
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected.getFormat(), actual.getFormat());
        Assert.assertEquals(expected.getFormattedMessage(), actual.getFormattedMessage());
        Assert.assertArrayEquals(expected.getParameters(), actual.getParameters());
    }
