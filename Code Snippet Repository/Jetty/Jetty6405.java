    public static URI toWebsocket(final URI inputUri) throws URISyntaxException
    {
        Objects.requireNonNull(inputUri,"Input URI must not be null");
        String httpScheme = inputUri.getScheme();
        if ("ws".equalsIgnoreCase(httpScheme) || "wss".equalsIgnoreCase(httpScheme))
        {
            // keep as-is
            return inputUri;
        }
        
        if ("http".equalsIgnoreCase(httpScheme))
        {
            // convert to ws
            return new URI("ws" + inputUri.toString().substring(httpScheme.length()));
        }
        
        if ("https".equalsIgnoreCase(httpScheme))
        {
            // convert to wss
            return new URI("wss" + inputUri.toString().substring(httpScheme.length()));
        }
        
        throw new URISyntaxException(inputUri.toString(),"Unrecognized HTTP scheme");
    }
