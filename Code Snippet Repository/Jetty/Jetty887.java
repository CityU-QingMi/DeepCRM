    protected void customizeFastCGIHeaders(Request proxyRequest, HttpFields fastCGIHeaders)
    {
        fastCGIHeaders.remove("HTTP_PROXY");

        fastCGIHeaders.put(FCGI.Headers.REMOTE_ADDR, (String)proxyRequest.getAttributes().get(REMOTE_ADDR_ATTRIBUTE));
        fastCGIHeaders.put(FCGI.Headers.REMOTE_PORT, (String)proxyRequest.getAttributes().get(REMOTE_PORT_ATTRIBUTE));
        fastCGIHeaders.put(FCGI.Headers.SERVER_NAME, (String)proxyRequest.getAttributes().get(SERVER_NAME_ATTRIBUTE));
        fastCGIHeaders.put(FCGI.Headers.SERVER_ADDR, (String)proxyRequest.getAttributes().get(SERVER_ADDR_ATTRIBUTE));
        fastCGIHeaders.put(FCGI.Headers.SERVER_PORT, (String)proxyRequest.getAttributes().get(SERVER_PORT_ATTRIBUTE));

        if (fcgiHTTPS || HttpScheme.HTTPS.is((String)proxyRequest.getAttributes().get(SCHEME_ATTRIBUTE)))
            fastCGIHeaders.put(FCGI.Headers.HTTPS, "on");

        URI proxyRequestURI = proxyRequest.getURI();
        String rawPath = proxyRequestURI == null ? proxyRequest.getPath() : proxyRequestURI.getRawPath();
        String rawQuery = proxyRequestURI == null ? null : proxyRequestURI.getRawQuery();

        String requestURI = (String)proxyRequest.getAttributes().get(REQUEST_URI_ATTRIBUTE);
        if (requestURI == null)
        {
            requestURI = rawPath;
            if (rawQuery != null)
                requestURI += "?" + rawQuery;
        }
        fastCGIHeaders.put(FCGI.Headers.REQUEST_URI, requestURI);

        String requestQuery = (String)proxyRequest.getAttributes().get(REQUEST_QUERY_ATTRIBUTE);
        if (requestQuery != null)
            fastCGIHeaders.put(FCGI.Headers.QUERY_STRING, requestQuery);

        String scriptName = rawPath;
        Matcher matcher = scriptPattern.matcher(rawPath);
        if (matcher.matches())
        {
            // Expect at least one group in the regular expression.
            scriptName = matcher.group(1);

            // If there is a second group, map it to PATH_INFO.
            if (matcher.groupCount() > 1)
                fastCGIHeaders.put(FCGI.Headers.PATH_INFO, matcher.group(2));
        }
        fastCGIHeaders.put(FCGI.Headers.SCRIPT_NAME, scriptName);

        String root = fastCGIHeaders.get(FCGI.Headers.DOCUMENT_ROOT);
        fastCGIHeaders.put(FCGI.Headers.SCRIPT_FILENAME, root + scriptName);
    }
