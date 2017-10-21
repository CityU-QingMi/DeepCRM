    @Test
    public void testNoSettingNoConfig03() {
        final ColumnConfig config = ColumnConfig.newBuilder()
            .setName("col")
            .setPattern(Strings.EMPTY)
            .setLiteral(Strings.EMPTY)
            .setEventTimestamp(false)
            .build();

        assertNull("The result should be null.", config);
    }
