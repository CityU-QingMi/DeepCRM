    public void init(JsrSession session)
    {
        // Default for the session.
        // Session is an optional parameter (always)
        idxSession = findIndexForRole(Param.Role.SESSION);
        if (idxSession >= 0)
        {
            args[idxSession] = session;
        }

        // Optional EndpointConfig
        idxConfig = findIndexForRole(Param.Role.ENDPOINT_CONFIG);
        if (idxConfig >= 0)
        {
            args[idxConfig] = session.getEndpointConfig();
        }

        // Default for the path parameters
        // PathParam's are optional parameters (always)
        Map<String, String> pathParams = session.getPathParameters();
        if ((pathParams != null) && (pathParams.size() > 0))
        {
            for (Param param : params)
            {
                if (param.role == Role.PATH_PARAM)
                {
                    int idx = param.index;
                    String rawvalue = pathParams.get(param.getPathParamName());

                    Decoder decoder = session.getDecoderFactory().getDecoderFor(param.type);
                    if (decoder instanceof Decoder.Text<?>)
                    {
                        Decoder.Text<?> textDecoder = (Decoder.Text<?>)decoder;
                        try
                        {
                            args[idx] = textDecoder.decode(rawvalue);
                        }
                        catch (DecodeException e)
                        {
                            session.notifyError(e);
                        }
                    }
                    else
                    {
                        throw new InvalidWebSocketException("PathParam decoders must use Decoder.Text");
                    }
                }
            }
        }
    }
