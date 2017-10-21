    public HttpCookie getSessionCookie(HttpSession session, String contextPath, boolean requestIsSecure)
    {
        if (isUsingCookies())
        {
            String sessionPath = (_cookieConfig.getPath()==null) ? contextPath : _cookieConfig.getPath();
            sessionPath = (sessionPath==null||sessionPath.length()==0) ? "/" : sessionPath;
            String id = getExtendedId(session);
            HttpCookie cookie = null;
            if (_sessionComment == null)
            {
                cookie = new HttpCookie(
                                        _cookieConfig.getName(),
                                        id,
                                        _cookieConfig.getDomain(),
                                        sessionPath,
                                        _cookieConfig.getMaxAge(),
                                        _cookieConfig.isHttpOnly(),
                                        _cookieConfig.isSecure() || (isSecureRequestOnly() && requestIsSecure));
            }
            else
            {
                cookie = new HttpCookie(
                                        _cookieConfig.getName(),
                                        id,
                                        _cookieConfig.getDomain(),
                                        sessionPath,
                                        _cookieConfig.getMaxAge(),
                                        _cookieConfig.isHttpOnly(),
                                        _cookieConfig.isSecure() || (isSecureRequestOnly() && requestIsSecure),
                                        _sessionComment,
                                        1);
            }

            return cookie;
        }
        return null;
    }
