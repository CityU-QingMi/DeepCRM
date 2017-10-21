    @Override
    public Authentication validateRequest(ServletRequest req, ServletResponse res, boolean mandatory) throws ServerAuthException
    {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        String credentials = request.getHeader(HttpHeader.AUTHORIZATION.asString());

        try
        {
            if (!mandatory)
                return new DeferredAuthentication(this);

            if (credentials != null)
            {
                int space=credentials.indexOf(' ');
                if (space>0)
                {
                    String method=credentials.substring(0,space);
                    if ("basic".equalsIgnoreCase(method))
                    {
                        credentials = credentials.substring(space+1);
                        credentials = B64Code.decode(credentials, StandardCharsets.ISO_8859_1);
                        int i = credentials.indexOf(':');
                        if (i>0)
                        {
                            String username = credentials.substring(0,i);
                            String password = credentials.substring(i+1);

                            UserIdentity user = login (username, password, request);
                            if (user!=null)
                            {
                                return new UserAuthentication(getAuthMethod(),user);
                            }
                        }
                    }
                }
            }

            if (DeferredAuthentication.isDeferred(response))
                return Authentication.UNAUTHENTICATED;

            response.setHeader(HttpHeader.WWW_AUTHENTICATE.asString(), "basic realm=\"" + _loginService.getName() + '"');
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return Authentication.SEND_CONTINUE;
        }
        catch (IOException e)
        {
            throw new ServerAuthException(e);
        }
    }
