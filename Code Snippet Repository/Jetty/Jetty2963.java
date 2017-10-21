    @Override
    public void addCookie(Cookie cookie)
    {
        String comment = cookie.getComment();
        boolean httpOnly = false;

        if (comment != null)
        {
            int i = comment.indexOf(HTTP_ONLY_COMMENT);
            if (i >= 0)
            {
                httpOnly = true;
                comment = comment.replace(HTTP_ONLY_COMMENT, "").trim();
                if (comment.length() == 0)
                    comment = null;
            }
        }
    
        if (StringUtil.isBlank(cookie.getName()))
        {
            throw new IllegalArgumentException("Cookie.name cannot be blank/null");
        }

        if (getHttpChannel().getHttpConfiguration().isCookieCompliance(CookieCompliance.RFC2965))
            addSetRFC2965Cookie(cookie.getName(),
                cookie.getValue(),
                cookie.getDomain(),
                cookie.getPath(),
                cookie.getMaxAge(),
                comment,
                cookie.getSecure(),
                httpOnly || cookie.isHttpOnly(),
                cookie.getVersion());
        else
            addSetRFC6265Cookie(cookie.getName(),
                cookie.getValue(),
                cookie.getDomain(),
                cookie.getPath(),
                cookie.getMaxAge(),
                cookie.getSecure(),
                httpOnly || cookie.isHttpOnly());
    }
