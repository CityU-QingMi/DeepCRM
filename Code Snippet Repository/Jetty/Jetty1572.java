    @Test
    public void testRequireValidRFC6265CookieValue_Bad()
    {
        String values[] = {
                "va\tlue",
                "\t",
                "value\u0000",
                "val\u0082ue",
                "va lue",
                "va;lue",
                "\"value",
                "value\"",
                "val\\ue",
                "val\"ue",
                "\""
        };
        
        for (String value : values)
        {
            try
            {
                Syntax.requireValidRFC6265CookieValue(value);
                fail("RFC6265 Cookie Value [" + value + "] Should have thrown " + IllegalArgumentException.class.getName());
            }
            catch (IllegalArgumentException e)
            {
                assertThat("Testing Bad RFC6265 Cookie Value [" + value + "]", e.getMessage(), containsString("RFC6265"));
            }
        }
    }
