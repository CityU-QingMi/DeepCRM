    @Test
    public void testRFC_Double()
    {
        String rawCookie = "$Version=\"1\"; " +
                "Customer=\"WILE_E_COYOTE\"; $Path=\"/acme\"; " +
                "Part_Number=\"Rocket_Launcher_0001\"; $Path=\"/acme\"";
        
        Cookie cookies[] = parseCookieHeaders(CookieCompliance.RFC2965,rawCookie);
        
        assertThat("Cookies.length", cookies.length, is(2));
        assertCookie("Cookies[0]", cookies[0], "Customer", "WILE_E_COYOTE", 1, "/acme");
        assertCookie("Cookies[1]", cookies[1], "Part_Number", "Rocket_Launcher_0001", 1, "/acme");
    }
