    @Test
    public void testPatternAndLiteralNoConfig() {
        final ColumnConfig config = ColumnConfig.newBuilder()
            .setName("col")
            .setPattern("%l")
            .setLiteral("literal")
            .build();

        assertNull("The result should be null.", config);
    }
