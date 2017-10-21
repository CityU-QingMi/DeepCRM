    @Override
    public URI getRequestURI()
    {
        try
        {
            String uriAsString = _req.getRequestURI();
            if (_req.getQueryString() != null)
            {
                uriAsString += "?" + _req.getQueryString();
            }

            return new URI(uriAsString);
        }
        catch (URISyntaxException ex)
        {
            throw new RuntimeException(ex);
        }
    }
