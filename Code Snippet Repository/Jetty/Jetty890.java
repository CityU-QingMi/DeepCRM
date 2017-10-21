    private Path toPath(URL url) throws IOException
    {
        try
        {
            return Paths.get(url.toURI());
        }
        catch (URISyntaxException x)
        {
            throw new IOException(x);
        }
    }
