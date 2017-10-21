    @Test
    public void testPatternColumn01() {
        final ColumnConfig config = ColumnConfig.newBuilder()
            .setName("col")
            .setPattern("%l")
            .build();

        assertNotNull("The result should not be null.", config);
        assertEquals("The column name is not correct.", "col", config.getColumnName());
        assertNotNull("The pattern should not be null.", config.getLayout());
        assertEquals("The pattern is not correct.", "%l", config.getLayout().toString());
        assertNull("The literal value should be null.", config.getLiteralValue());
        assertFalse("The timestamp flag should be false.", config.isEventTimestamp());
        assertTrue("The unicode flag should be true.", config.isUnicode());
        assertFalse("The clob flag should be false.", config.isClob());
    }
