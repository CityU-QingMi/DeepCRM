    private void handlePreflightResponse(HttpServletRequest request, HttpServletResponse response, String origin)
    {
        boolean methodAllowed = isMethodAllowed(request);

        if (!methodAllowed)
            return;
        List<String> headersRequested = getAccessControlRequestHeaders(request);
        boolean headersAllowed = areHeadersAllowed(headersRequested);
        if (!headersAllowed)
            return;
        response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, origin);
        //W3C CORS spec http://www.w3.org/TR/cors/#resource-implementation
        if (!anyOriginAllowed)
            response.addHeader("Vary", ORIGIN_HEADER);
        if (allowCredentials)
            response.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS_HEADER, "true");
        if (preflightMaxAge > 0)
            response.setHeader(ACCESS_CONTROL_MAX_AGE_HEADER, String.valueOf(preflightMaxAge));
        response.setHeader(ACCESS_CONTROL_ALLOW_METHODS_HEADER, commify(allowedMethods));
        if (anyHeadersAllowed)
            response.setHeader(ACCESS_CONTROL_ALLOW_HEADERS_HEADER, commify(headersRequested));
        else
            response.setHeader(ACCESS_CONTROL_ALLOW_HEADERS_HEADER, commify(allowedHeaders));
    }
