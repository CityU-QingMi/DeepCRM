    @Test
    public void testRFC_PathExample()
    {
        String rawCookie = "$Version=\"1\"; " +
                "Part_Number=\"Riding_Rocket_0023\"; $Path=\"/acme/ammo\"; " +
                "Part_Number=\"Rocket_Launcher_0001\"; $Path=\"/acme\"";
        
        Cookie cookies[] = parseCookieHeaders(CookieCompliance.RFC2965,rawCookie);
        
        assertThat("Cookies.length", cookies.length, is(2));
        assertCookie("Cookies[0]", cookies[0], "Part_Number", "Riding_Rocket_0023", 1, "/acme/ammo");
        assertCookie("Cookies[1]", cookies[1], "Part_Number", "Rocket_Launcher_0001", 1, "/acme");
    }
