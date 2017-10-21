    @Test
    public void testCaseInsensitiveHeaderParsing()
    {
        assertFalse(_authenticator.isAuthSchemeNegotiate(null));
        assertFalse(_authenticator.isAuthSchemeNegotiate(""));
        assertFalse(_authenticator.isAuthSchemeNegotiate("Basic"));
        assertFalse(_authenticator.isAuthSchemeNegotiate("basic"));
        assertFalse(_authenticator.isAuthSchemeNegotiate("Digest"));
        assertFalse(_authenticator.isAuthSchemeNegotiate("LotsandLotsandLots of nonsense"));
        assertFalse(_authenticator.isAuthSchemeNegotiate("Negotiat asdfasdf"));
        assertFalse(_authenticator.isAuthSchemeNegotiate("Negotiated"));
        assertFalse(_authenticator.isAuthSchemeNegotiate("Negotiate-and-more"));

        assertTrue(_authenticator.isAuthSchemeNegotiate("Negotiate"));
        assertTrue(_authenticator.isAuthSchemeNegotiate("negotiate"));
        assertTrue(_authenticator.isAuthSchemeNegotiate("negOtiAte"));
    }
