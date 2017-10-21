    public static URI toHttp(final URI inputUri) throws URISyntaxException
    {
        Objects.requireNonNull(inputUri,"Input URI must not be null");
        String wsScheme = inputUri.getScheme();
        if ("http".equalsIgnoreCase(wsScheme) || "https".equalsIgnoreCase(wsScheme))
        {
            // leave alone
            return inputUri;
        }
        
        if ("ws".equalsIgnoreCase(wsScheme))
        {
            // convert to http
            return new URI("http" + inputUri.toString().substring(wsScheme.length()));
        }
        
        if ("wss".equalsIgnoreCase(wsScheme))
        {
            // convert to https
            return new URI("https" + inputUri.toString().substring(wsScheme.length()));
        }
        
        throw new URISyntaxException(inputUri.toString(),"Unrecognized WebSocket scheme");
    }
