    @Test
    public void testRFC2109_CookieSpoofingExample()
    {
        String rawCookie = "$Version=\"1\"; " +
                "session_id=\"1234\"; " +
                "session_id=\"1111\"; $Domain=\".cracker.edu\"";
        
        Cookie cookies[] = parseCookieHeaders(CookieCompliance.RFC2965,rawCookie);
        
        assertThat("Cookies.length", cookies.length, is(2));
        assertCookie("Cookies[0]", cookies[0], "session_id", "1234", 1, null);
        assertCookie("Cookies[1]", cookies[1], "session_id", "1111", 1, null);
    }
