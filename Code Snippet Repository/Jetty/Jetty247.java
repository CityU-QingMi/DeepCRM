    protected Request copyRequest(HttpRequest oldRequest, URI newURI)
    {
        Request newRequest = newHttpRequest(oldRequest.getConversation(), newURI);
        newRequest.method(oldRequest.getMethod())
                .version(oldRequest.getVersion())
                .content(oldRequest.getContent())
                .idleTimeout(oldRequest.getIdleTimeout(), TimeUnit.MILLISECONDS)
                .timeout(oldRequest.getTimeout(), TimeUnit.MILLISECONDS)
                .followRedirects(oldRequest.isFollowRedirects());
        for (HttpField field : oldRequest.getHeaders())
        {
            HttpHeader header = field.getHeader();
            // We have a new URI, so skip the host header if present.
            if (HttpHeader.HOST == header)
                continue;

            // Remove expectation headers.
            if (HttpHeader.EXPECT == header)
                continue;

            // Remove cookies.
            if (HttpHeader.COOKIE == header)
                continue;

            // Remove authorization headers.
            if (HttpHeader.AUTHORIZATION == header ||
                    HttpHeader.PROXY_AUTHORIZATION == header)
                continue;

            String name = field.getName();
            String value = field.getValue();
            if (!newRequest.getHeaders().contains(name, value))
                newRequest.header(name, value);
        }
        return newRequest;
    }
