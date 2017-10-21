    @Test
    public void testRequireValidRFC2616Token_Good()
    {
        String tokens[] = {
                "name",
                "",
                null,
                "n.a.m.e",
                "na-me",
                "+name",
                "na*me",
                "na$me",
                "#name"
        };
        
        for (String token : tokens)
        {
            Syntax.requireValidRFC2616Token(token, "Test Based");
            // No exception should occur here
        }
    }
