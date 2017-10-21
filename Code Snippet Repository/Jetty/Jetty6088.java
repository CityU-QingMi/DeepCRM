    @Override
    public boolean process(Param param, JsrCallable callable) throws InvalidSignatureException
    {
        // Session parameter (optional)
        if (param.type.isAssignableFrom(Session.class))
        {
            param.bind(Role.SESSION);
            return true;
        }

        // Endpoint Config (optional)
        if (param.type.isAssignableFrom(EndpointConfig.class))
        {
            param.bind(Role.ENDPOINT_CONFIG);
            return true;
        }

        return false;
    }
