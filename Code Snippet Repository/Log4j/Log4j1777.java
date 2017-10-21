    @Test
    public void testLiteralAndDateNoConfig() {
        final ColumnConfig config = ColumnConfig.newBuilder()
            .setName("col")
            .setLiteral("literal")
            .setEventTimestamp(true)
            .build();

        assertNull("The result should be null.", config);
    }
