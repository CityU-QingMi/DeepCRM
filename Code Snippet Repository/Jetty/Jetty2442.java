    @Test
    public void testProxyAuthenticationWithIncludedAddressWithResponseContent() throws Exception
    {
        final String realm = "test-realm";
        testProxyAuthentication(realm, new ConnectHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                String proxyAuth = request.getHeader(HttpHeader.PROXY_AUTHORIZATION.asString());
                if (proxyAuth == null)
                {
                    baseRequest.setHandled(true);
                    response.setStatus(HttpStatus.PROXY_AUTHENTICATION_REQUIRED_407);
                    response.setHeader(HttpHeader.PROXY_AUTHENTICATE.asString(), "Basic realm=\"" + realm + "\"");
                    response.getOutputStream().write(new byte[1024]);
                    return;
                }
                super.handle(target, baseRequest, request, response);
            }
        }, true);
    }
