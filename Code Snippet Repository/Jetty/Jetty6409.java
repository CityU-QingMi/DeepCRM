    public ClientUpgradeRequest(WebSocketUpgradeRequest wsRequest)
    {
        this(wsRequest.getURI());
        // cookies
        this.setCookies(wsRequest.getCookies());
        // headers
        Map<String, List<String>> headers = new HashMap<>();
        HttpFields fields = wsRequest.getHeaders();
        for (HttpField field : fields)
        {
            String key = field.getName();
            List<String> values = headers.get(key);
            if (values == null)
            {
                values = new ArrayList<>();
            }
            values.addAll(Arrays.asList(field.getValues()));
            headers.put(key,values);
            // sub protocols
            if(key.equalsIgnoreCase("Sec-WebSocket-Protocol"))
            {
                for(String subProtocol: field.getValue().split(","))
                {
                    setSubProtocols(subProtocol);
                }
            }
            // extensions
            if(key.equalsIgnoreCase("Sec-WebSocket-Extensions"))
            {
                for(ExtensionConfig ext: ExtensionConfig.parseList(field.getValues()))
                {
                    addExtensions(ext);
                }
            }
        }
        super.setHeaders(headers);
        // sessions
        setHttpVersion(wsRequest.getVersion().toString());
        setMethod(wsRequest.getMethod());
    }
