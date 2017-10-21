    @Test
    public void testPatternColumn02() {
        final ColumnConfig config = ColumnConfig.newBuilder()
            .setName("col2")
            .setPattern("%X{id} %level")
            .setLiteral(Strings.EMPTY)
            .setEventTimestamp(false)
            .setUnicode(false)
            .setClob(true)
            .build();

        assertNotNull("The result should not be null.", config);
        assertEquals("The column name is not correct.", "col2", config.getColumnName());
        assertNotNull("The pattern should not be null.", config.getLayout());
        assertEquals("The pattern is not correct.", "%X{id} %level", config.getLayout().toString());
        assertNull("The literal value should be null.", config.getLiteralValue());
        assertFalse("The timestamp flag should be false.", config.isEventTimestamp());
        assertFalse("The unicode flag should be false.", config.isUnicode());
        assertTrue("The clob flag should be true.", config.isClob());
    }
