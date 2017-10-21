    @Test
    public void testKeyValue()
    {
        String rawCookie = "key=value";
        
        Cookie cookies[] = parseCookieHeaders(CookieCompliance.RFC6265,rawCookie);
        
        assertThat("Cookies.length", cookies.length, is(1));
        assertCookie("Cookies[0]", cookies[0], "key", "value", 0, null);
    }
