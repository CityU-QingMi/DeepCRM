    protected HttpRequest(HttpClient client, HttpConversation conversation, URI uri)
    {
        this.client = client;
        this.conversation = conversation;
        scheme = uri.getScheme();
        host = client.normalizeHost(uri.getHost());
        port = HttpClient.normalizePort(scheme, uri.getPort());
        path = uri.getRawPath();
        query = uri.getRawQuery();
        extractParams(query);

        followRedirects(client.isFollowRedirects());
        idleTimeout = client.getIdleTimeout();
        HttpField acceptEncodingField = client.getAcceptEncodingField();
        if (acceptEncodingField != null)
            headers.put(acceptEncodingField);
        HttpField userAgentField = client.getUserAgentField();
        if (userAgentField != null)
            headers.put(userAgentField);
    }
