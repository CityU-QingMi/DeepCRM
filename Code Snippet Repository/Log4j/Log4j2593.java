    @Test
    public void testEquals() {
        final DateParser parser1= getInstance(YMD_SLASH);
        final DateParser parser2= getInstance(YMD_SLASH);

        assertEquals(parser1, parser2);
        assertEquals(parser1.hashCode(), parser2.hashCode());

        assertFalse(parser1.equals(new Object()));
    }
