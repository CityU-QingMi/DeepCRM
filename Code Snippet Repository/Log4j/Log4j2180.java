    private void testIoContainer(final ObjectMapper objectMapper ) throws IOException {
        final Fixture expected = new Fixture();
        final String s = objectMapper.writeValueAsString(expected);
        final Fixture actual = objectMapper.readValue(s, Fixture.class);
        assertEquals(expected.proxy.getName(), actual.proxy.getName());
        assertEquals(expected.proxy.getMessage(), actual.proxy.getMessage());
        assertEquals(expected.proxy.getLocalizedMessage(), actual.proxy.getLocalizedMessage());
        assertEquals(expected.proxy.getCommonElementCount(), actual.proxy.getCommonElementCount());
        assertArrayEquals(expected.proxy.getExtendedStackTrace(), actual.proxy.getExtendedStackTrace());
        assertEquals(expected.proxy, actual.proxy);
    }
