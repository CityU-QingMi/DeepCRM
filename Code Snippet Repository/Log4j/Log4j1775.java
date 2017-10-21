    @Test
    public void testLiteralColumn02() {
        final ColumnConfig config = ColumnConfig.newBuilder()
            .setName("col2")
            .setLiteral("USER1.MY_SEQUENCE.NEXT")
            .setUnicode(true)
            .setClob(true)
            .build();

        assertNotNull("The result should not be null.", config);
        assertEquals("The column name is not correct.", "col2", config.getColumnName());
        assertNull("The pattern should be null.", config.getLayout());
        assertNotNull("The literal value should be null.", config.getLiteralValue());
        assertEquals("The literal value is not correct.", "USER1.MY_SEQUENCE.NEXT", config.getLiteralValue());
        assertFalse("The timestamp flag should be false.", config.isEventTimestamp());
        assertFalse("The unicode flag should be false.", config.isUnicode());
        assertFalse("The clob flag should be false.", config.isClob());
    }
