    @Test
    public void testRFC6265_SidLangExample()
    {
        String rawCookie = "SID=31d4d96e407aad42; lang=en-US";
        
        Cookie cookies[] = parseCookieHeaders(CookieCompliance.RFC6265,rawCookie);
        
        assertThat("Cookies.length", cookies.length, is(2));
        assertCookie("Cookies[0]", cookies[0], "SID", "31d4d96e407aad42", 0, null);
        assertCookie("Cookies[1]", cookies[1], "lang", "en-US", 0, null);
    }
