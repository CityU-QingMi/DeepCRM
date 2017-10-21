    @Test
    public void testDateColumn02() {
        final ColumnConfig config = ColumnConfig.newBuilder()
            .setName("col2")
            .setEventTimestamp(true)
            .setUnicode(true)
            .setClob(true)
            .build();

        assertNotNull("The result should not be null.", config);
        assertEquals("The column name is not correct.", "col2", config.getColumnName());
        assertNull("The pattern should be null.", config.getLayout());
        assertNull("The literal value should be null.", config.getLiteralValue());
        assertTrue("The timestamp flag should be true.", config.isEventTimestamp());
        assertFalse("The unicode flag should be false.", config.isUnicode());
        assertFalse("The clob flag should be false.", config.isClob());
    }
