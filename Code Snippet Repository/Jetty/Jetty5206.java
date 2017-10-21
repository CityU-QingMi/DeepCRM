    public PathResource(URI uri) throws IOException
    {
        if (!uri.isAbsolute())
        {
            throw new IllegalArgumentException("not an absolute uri");
        }

        if (!uri.getScheme().equalsIgnoreCase("file"))
        {
            throw new IllegalArgumentException("not file: scheme");
        }

        Path path;
        try
        {
            path = Paths.get(uri);
        }
        catch (InvalidPathException e)
        {
            throw e;
        }
        catch (IllegalArgumentException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            LOG.ignore(e);
            throw new IOException("Unable to build Path from: " + uri,e);
        }

        this.path = path.toAbsolutePath();
        this.uri = path.toUri();
        this.alias = checkAliasPath();
    }
