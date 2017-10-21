    @OnMessage(maxMessageSize = 111222)
    public String echoText(String msg)
    {
        switch (msg)
        {
            case "text-max":
                return String.format(Locale.US, "%,d",session.getMaxTextMessageBufferSize());
            case "binary-max":
                return String.format(Locale.US, "%,d",session.getMaxBinaryMessageBufferSize());
            case "decoders":
                return join(config.getDecoders(),", ");
            case "encoders":
                return join(config.getEncoders(),", ");
            case "subprotocols":
                if (serverConfig == null)
                {
                    return "<not a ServerEndpointConfig>";
                }
                else
                {
                    List<String> protocols = new ArrayList<>();
                    protocols.addAll(serverConfig.getSubprotocols());
                    Collections.sort(protocols);
                    return join(protocols,", ");
                }
            case "configurator":
                if (serverConfig == null)
                {
                    return "<not a ServerEndpointConfig>";
                }
                else
                {
                    return serverConfig.getConfigurator().getClass().getName();
                }
            default:
                // normal echo
                return msg;
        }
    }
