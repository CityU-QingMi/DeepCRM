    @Test
    public void testUnquote()
    {
        String uq =" not quoted ";
        assertTrue(StringUtil.unquote(uq)==uq);
        assertEquals(StringUtil.unquote("' quoted string '")," quoted string ");
        assertEquals(StringUtil.unquote("\" quoted string \"")," quoted string ");
        assertEquals(StringUtil.unquote("' quoted\"string '")," quoted\"string ");
        assertEquals(StringUtil.unquote("\" quoted'string \"")," quoted'string ");
    }
