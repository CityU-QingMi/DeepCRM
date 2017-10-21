    @Test
    public void testProxyAuthenticationClosesConnection() throws Exception
    {
        final String realm = "test-realm";
        testProxyAuthentication(realm, new ConnectHandler()
        {
            @Override
            protected boolean handleAuthentication(HttpServletRequest request, HttpServletResponse response, String address)
            {
                final String header = request.getHeader(HttpHeader.PROXY_AUTHORIZATION.toString());
                if (header == null || !header.startsWith("Basic "))
                {
                    response.setHeader(HttpHeader.PROXY_AUTHENTICATE.toString(), "Basic realm=\"" + realm + "\"");
                    // Returning false adds Connection: close to the 407 response.
                    return false;
                }
                else
                {
                    return true;
                }
            }
        });
    }
