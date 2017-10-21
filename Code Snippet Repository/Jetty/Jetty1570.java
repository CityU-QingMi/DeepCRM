    @Test
    public void testRequireValidRFC2616Token_Bad()
    {
        String tokens[] = {
                "\"name\"",
                "name\t",
                "na me",
                "name\u0082",
                "na\tme",
                "na;me",
                "{name}",
                "[name]",
                "\""
        };
        
        for (String token : tokens)
        {
            try
            {
                Syntax.requireValidRFC2616Token(token, "Test Based");
                fail("RFC2616 Token [" + token + "] Should have thrown " + IllegalArgumentException.class.getName());
            }
            catch (IllegalArgumentException e)
            {
                assertThat("Testing Bad RFC2616 Token [" + token + "]", e.getMessage(),
                        allOf(containsString("Test Based"),
                                containsString("RFC2616")));
            }
        }
    }
