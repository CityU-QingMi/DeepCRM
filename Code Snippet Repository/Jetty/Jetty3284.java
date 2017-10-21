    @Test
    public void testRFC6265_SidExample()
    {
        String rawCookie = "SID=31d4d96e407aad42";
        
        Cookie cookies[] = parseCookieHeaders(CookieCompliance.RFC6265,rawCookie);
        
        assertThat("Cookies.length", cookies.length, is(1));
        assertCookie("Cookies[0]", cookies[0], "SID", "31d4d96e407aad42", 0, null);
    }
