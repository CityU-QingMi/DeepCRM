    public UpgradeHttpServletRequest(HttpServletRequest httpRequest)
    {
        // The original request object must be held temporarily for the duration of the handshake
        // in order to be able to implement methods such as isUserInRole() and setAttribute().
        request = httpRequest;
        context = httpRequest.getServletContext();
        dispatcher = httpRequest.getDispatcherType();

        method = httpRequest.getMethod();
        protocol = httpRequest.getProtocol();
        scheme = httpRequest.getScheme();
        secure = httpRequest.isSecure();
        requestURI = httpRequest.getRequestURI();
        requestURL = httpRequest.getRequestURL();
        pathInfo = httpRequest.getPathInfo();
        pathTranslated = httpRequest.getPathTranslated();
        servletPath = httpRequest.getServletPath();
        query = httpRequest.getQueryString();
        authType = httpRequest.getAuthType();
        cookies = request.getCookies();

        remoteUser = httpRequest.getRemoteUser();
        principal = httpRequest.getUserPrincipal();

        Enumeration<String> headerNames = httpRequest.getHeaderNames();
        while (headerNames.hasMoreElements())
        {
            String name = headerNames.nextElement();
            headers.put(name, Collections.list(httpRequest.getHeaders(name)));
        }

        parameters.putAll(request.getParameterMap());

        Enumeration<String> attributeNames = httpRequest.getAttributeNames();
        while (attributeNames.hasMoreElements())
        {
            String name = attributeNames.nextElement();
            attributes.put(name, httpRequest.getAttribute(name));
        }

        localAddress = InetSocketAddress.createUnresolved(httpRequest.getLocalAddr(), httpRequest.getLocalPort());
        localName = httpRequest.getLocalName();
        remoteAddress = InetSocketAddress.createUnresolved(httpRequest.getRemoteAddr(), httpRequest.getRemotePort());
        remoteName = httpRequest.getRemoteHost();
        serverAddress = InetSocketAddress.createUnresolved(httpRequest.getServerName(), httpRequest.getServerPort());
    }
