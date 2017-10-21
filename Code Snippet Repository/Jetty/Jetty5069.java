    public static URI getJarSource(URI uri)
    {
        try
        {
            if (!"jar".equals(uri.getScheme()))
                return uri;
            // Get SSP (retaining encoded form)
            String s = uri.getRawSchemeSpecificPart();
            int bang_slash = s.indexOf("!/");
            if (bang_slash>=0)
                s=s.substring(0,bang_slash);
            return new URI(s);
        }
        catch(URISyntaxException e)
        {
            throw new IllegalArgumentException(e);
        }
    }
