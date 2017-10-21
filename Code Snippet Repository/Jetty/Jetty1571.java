    @Test
    public void testRequireValidRFC6265CookieValue_Good()
    {
        String values[] = {
                "value",
                "",
                null,
                "val=ue",
                "val-ue",
                "\"value\"",
                "val/ue",
                "v.a.l.u.e"
        };
        
        for (String value : values)
        {
            Syntax.requireValidRFC6265CookieValue(value);
            // No exception should occur here
        }
    }
