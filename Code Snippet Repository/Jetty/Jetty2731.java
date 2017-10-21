    @Test
    public void testChallengeSentWithNoAuthorization() throws Exception
    {
        HttpChannel channel = new HttpChannel(null, new HttpConfiguration(), null, null)
        {
            @Override
            public Server getServer()
            {
                return null;
            }
        };
        Request req = new Request(channel, null);
        HttpOutput out = new HttpOutput(channel)
        {
            @Override
            public void close()
            {
                return;
            }
        };
        Response res = new Response(channel, out);
        MetaData.Request metadata = new MetaData.Request(new HttpFields());
        metadata.setURI(new HttpURI("http://localhost"));
        req.setMetaData(metadata);
        
        assertEquals(Authentication.SEND_CONTINUE, _authenticator.validateRequest(req, res, true));
        assertEquals(HttpHeader.NEGOTIATE.asString(), res.getHeader(HttpHeader.WWW_AUTHENTICATE.asString()));
        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, res.getStatus());
    }
