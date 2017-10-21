    @Test
    public void testReplace()
    {
        String s="\u0690bc \u0690bc \u0690bc";
        assertEquals(StringUtil.replace(s, "\u0690bc", "xyz"),"xyz xyz xyz");
        assertTrue(StringUtil.replace(s,"xyz","pqy")==s);

        s=" \u0690bc ";
        assertEquals(StringUtil.replace(s, "\u0690bc", "xyz")," xyz ");

    }
