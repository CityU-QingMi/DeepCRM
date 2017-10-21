    @Test
    public void testChallengeSentWithUnhandledAuthorization() throws Exception
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
        HttpFields http_fields = new HttpFields();
        // Create a bogus Authorization header. We don't care about the actual credentials.
        http_fields.add(HttpHeader.AUTHORIZATION, "Basic asdf");
        MetaData.Request metadata = new MetaData.Request(http_fields);
        metadata.setURI(new HttpURI("http://localhost"));
        req.setMetaData(metadata);

        assertEquals(Authentication.SEND_CONTINUE, _authenticator.validateRequest(req, res, true));
        assertEquals(HttpHeader.NEGOTIATE.asString(), res.getHeader(HttpHeader.WWW_AUTHENTICATE.asString()));
        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, res.getStatus());
    }
