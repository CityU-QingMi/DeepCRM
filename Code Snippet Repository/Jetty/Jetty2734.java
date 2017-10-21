    @Test
    public void testExtractAuthScheme()
    {
        assertEquals("", _authenticator.getAuthSchemeFromHeader(null));
        assertEquals("", _authenticator.getAuthSchemeFromHeader(""));
        assertEquals("", _authenticator.getAuthSchemeFromHeader("   "));
        assertEquals("Basic", _authenticator.getAuthSchemeFromHeader(" Basic asdfasdf"));
        assertEquals("Basicasdf", _authenticator.getAuthSchemeFromHeader("Basicasdf asdfasdf"));
        assertEquals("basic", _authenticator.getAuthSchemeFromHeader(" basic asdfasdf "));
        assertEquals("Negotiate", _authenticator.getAuthSchemeFromHeader("Negotiate asdfasdf"));
        assertEquals("negotiate", _authenticator.getAuthSchemeFromHeader("negotiate asdfasdf"));
        assertEquals("negotiate", _authenticator.getAuthSchemeFromHeader(" negotiate  asdfasdf"));
        assertEquals("negotiated", _authenticator.getAuthSchemeFromHeader(" negotiated  asdfasdf"));
    }
