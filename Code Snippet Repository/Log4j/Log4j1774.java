    @Test
    public void testLiteralColumn01() {
        final ColumnConfig config = ColumnConfig.newBuilder()
            .setName("col")
            .setLiteral("literalValue01")
            .build();

        assertNotNull("The result should not be null.", config);
        assertEquals("The column name is not correct.", "col", config.getColumnName());
        assertNull("The pattern should be null.", config.getLayout());
        assertNotNull("The literal value should be null.", config.getLiteralValue());
        assertEquals("The literal value is not correct.", "literalValue01", config.getLiteralValue());
        assertFalse("The timestamp flag should be false.", config.isEventTimestamp());
        assertFalse("The unicode flag should be false.", config.isUnicode());
        assertFalse("The clob flag should be false.", config.isClob());
    }
