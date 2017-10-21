    private URI newURI(String uri)
    {
        try
        {
            // Handle specially the "OPTIONS *" case, since it is possible to create a URI from "*" (!).
            if ("*".equals(uri))
                return null;
            URI result = new URI(uri);
            return result.isOpaque() ? null : result;
        }
        catch (URISyntaxException x)
        {
            // The "path" of a HTTP request may not be a URI,
            // for example for CONNECT 127.0.0.1:8080.
            return null;
        }
    }
