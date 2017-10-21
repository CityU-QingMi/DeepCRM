    @Override
    public Authentication.Result findAuthenticationResult(URI uri)
    {
        // TODO: I should match the longest URI
        for (Map.Entry<URI, Authentication.Result> entry : results.entrySet())
        {
            if (AbstractAuthentication.matchesURI(entry.getKey(), uri))
                return entry.getValue();
        }
        return null;
    }
