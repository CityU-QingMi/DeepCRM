    private static URI normalizeURI(File file, URI uri) throws URISyntaxException {
        String u =uri.toASCIIString();
        if (file.isDirectory())
        {
            if(!u.endsWith("/"))
                u+="/";
        }
        else if (file.exists() && u.endsWith("/"))
            u=u.substring(0,u.length()-1);
        return new URI(u);
    }
