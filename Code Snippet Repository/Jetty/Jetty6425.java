    private void initWebSocketHeaders()
    {
        method(HttpMethod.GET);
        version(HttpVersion.HTTP_1_1);

        // The Upgrade Headers
        header(HttpHeader.UPGRADE,"websocket");
        header(HttpHeader.CONNECTION,"Upgrade");

        // The WebSocket Headers
        header(HttpHeader.SEC_WEBSOCKET_KEY,genRandomKey());
        header(HttpHeader.SEC_WEBSOCKET_VERSION,"13");

        // (Per the hybi list): Add no-cache headers to avoid compatibility issue.
        // There are some proxies that rewrite "Connection: upgrade"
        // to "Connection: close" in the response if a request doesn't contain
        // these headers.
        header(HttpHeader.PRAGMA,"no-cache");
        header(HttpHeader.CACHE_CONTROL,"no-cache");

        // handle "Sec-WebSocket-Extensions"
        if (!apiRequestFacade.getExtensions().isEmpty())
        {
            for (ExtensionConfig ext : apiRequestFacade.getExtensions())
            {
                header(HttpHeader.SEC_WEBSOCKET_EXTENSIONS,ext.getParameterizedName());
            }
        }

        // handle "Sec-WebSocket-Protocol"
        if (!apiRequestFacade.getSubProtocols().isEmpty())
        {
            for (String protocol : apiRequestFacade.getSubProtocols())
            {
                header(HttpHeader.SEC_WEBSOCKET_SUBPROTOCOL,protocol);
            }
        }
        
        if (upgradeListener != null)
        {
            upgradeListener.onHandshakeRequest(apiRequestFacade);
        }
    }
