    @Override
    public boolean process(Param param, JsrCallable callable) throws InvalidSignatureException
    {
        if (super.process(param,callable))
        {
            // Found common roles
            return true;
        }

        if (param.type.isAssignableFrom(EndpointConfig.class))
        {
            param.bind(Role.ENDPOINT_CONFIG);
            return true;
        }
        return false;
    }
