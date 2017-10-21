    private static URI toCanonicalURI(URI uri)
    {
        uri = uri.normalize();
        String ascii = uri.toASCIIString();
        if (ascii.endsWith("/"))
        {
            try
            {
                uri = new URI(ascii.substring(0,ascii.length()-1));
            }
            catch(URISyntaxException e)
            {
                throw new IllegalArgumentException(e);
            }
        }
        return uri;
    }
