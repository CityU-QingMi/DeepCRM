    protected String normalizeUri(URI uri)
    {
        for (URIAttribute a : uris)
        {
            try
            {
                if (uri.compareTo(a.uri)==0)
                    return String.format("${%s}",a.key);

                if (!a.uri.getScheme().equalsIgnoreCase(uri.getScheme()))
                    continue;
                if (a.uri.getHost()==null && uri.getHost()!=null)
                    continue;
                if (a.uri.getHost()!=null && !a.uri.getHost().equals(uri.getHost()))
                    continue;

                if (a.uri.getPath().equals(uri.getPath()))
                    return a.value;

                if (!uri.getPath().startsWith(a.uri.getPath()))
                    continue;

                String s = uri.getPath().substring(a.uri.getPath().length());

                if (s.charAt(0)!='/')
                    continue;

                return String.format("${%s}%s",a.key,new URI(s).toASCIIString());
            }
            catch(URISyntaxException e)
            {
                LOG.ignore(e);
            }
        }
        return uri.toASCIIString();
    }
