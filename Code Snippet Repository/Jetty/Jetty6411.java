    public ClientUpgradeResponse(HttpResponse response)
    {
        super();
        setStatusCode(response.getStatus());
        setStatusReason(response.getReason());

        HttpFields fields = response.getHeaders();
        for (HttpField field : fields)
        {
            addHeader(field.getName(),field.getValue());
        }

        HttpField extensionsField = fields.getField(HttpHeader.SEC_WEBSOCKET_EXTENSIONS);
        if (extensionsField != null)
            this.extensions = ExtensionConfig.parseList(extensionsField.getValues());
        setAcceptedSubProtocol(fields.get(HttpHeader.SEC_WEBSOCKET_SUBPROTOCOL));
    }
