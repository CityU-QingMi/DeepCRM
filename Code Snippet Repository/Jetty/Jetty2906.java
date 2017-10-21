    @Override
    public Cookie[] getCookies()
    {
        MetaData.Request metadata = _metaData;
        if (metadata==null || _cookiesExtracted)
        {
            if (_cookies == null || _cookies.getCookies().length == 0)
                return null;

            return _cookies.getCookies();
        }

        _cookiesExtracted = true;
        
        for (String c : metadata.getFields().getValuesList(HttpHeader.COOKIE))
        {
            if (_cookies == null)
                _cookies = new CookieCutter(getHttpChannel().getHttpConfiguration().getCookieCompliance());
            _cookies.addCookieField(c);
        }

        //Javadoc for Request.getCookies() stipulates null for no cookies
        if (_cookies == null || _cookies.getCookies().length == 0)
            return null;

        return _cookies.getCookies();
    }
