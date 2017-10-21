    @Test
    public void testPatternAndDateNoConfig() {
        final ColumnConfig config = ColumnConfig.newBuilder()
            .setName("col")
            .setPattern("%l")
            .setEventTimestamp(true)
            .build();

        assertNull("The result should be null.", config);
    }
