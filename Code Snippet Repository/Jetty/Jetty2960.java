    public void addCookie(HttpCookie cookie)
    {
        if (StringUtil.isBlank(cookie.getName()))
        {
            throw new IllegalArgumentException("Cookie.name cannot be blank/null");
        }
        
        if (getHttpChannel().getHttpConfiguration().isCookieCompliance(CookieCompliance.RFC2965))
            addSetRFC2965Cookie(
                cookie.getName(),
                cookie.getValue(),
                cookie.getDomain(),
                cookie.getPath(),
                cookie.getMaxAge(),
                cookie.getComment(),
                cookie.isSecure(),
                cookie.isHttpOnly(),
                cookie.getVersion());
        else
            addSetRFC6265Cookie(
                cookie.getName(),
                cookie.getValue(),
                cookie.getDomain(),
                cookie.getPath(),
                cookie.getMaxAge(),
                cookie.isSecure(),
                cookie.isHttpOnly());
    }
