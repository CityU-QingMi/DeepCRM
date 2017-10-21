    public ServletUpgradeRequest(HttpServletRequest httpRequest) throws URISyntaxException
    {
        this.queryString = httpRequest.getQueryString();
        this.secure = httpRequest.isSecure();

        StringBuffer uri = httpRequest.getRequestURL();
        if (this.queryString!=null)
            uri.append("?").append(this.queryString);
        uri.replace(0,uri.indexOf(":"),secure ? "wss" : "ws");        
        this.requestURI = new URI(uri.toString());
        this.request = new UpgradeHttpServletRequest(httpRequest);
    }
