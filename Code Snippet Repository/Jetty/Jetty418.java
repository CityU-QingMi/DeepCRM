    public static boolean matchesURI(URI uri1, URI uri2)
    {
        String scheme = uri1.getScheme();
        if (scheme.equalsIgnoreCase(uri2.getScheme()))
        {
            if (uri1.getHost().equalsIgnoreCase(uri2.getHost()))
            {
                // Handle default HTTP ports.
                int thisPort = HttpClient.normalizePort(scheme, uri1.getPort());
                int thatPort = HttpClient.normalizePort(scheme, uri2.getPort());
                if (thisPort == thatPort)
                {
                    // Use decoded URI paths.
                    return uri2.getPath().startsWith(uri1.getPath());
                }
            }
        }
        return false;
    }
