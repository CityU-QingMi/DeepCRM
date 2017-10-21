    @Test
    public void testRFC_Single()
    {
        String rawCookie = "$Version=\"1\"; Customer=\"WILE_E_COYOTE\"; $Path=\"/acme\"";
        
        Cookie cookies[] = parseCookieHeaders(CookieCompliance.RFC2965,rawCookie);
        
        assertThat("Cookies.length", cookies.length, is(1));
        assertCookie("Cookies[0]", cookies[0], "Customer", "WILE_E_COYOTE", 1, "/acme");
    }
