    public FileResource(File base, String childPath)
    {
        String encoded = URIUtil.encodePath(childPath);

        _file = new File(base, childPath);

        // The encoded path should be a suffix of the resource (give or take a directory / )
        URI uri;
        try
        {
            if (base.isDirectory())
            {
                // treat all paths being added as relative
                uri=new URI(URIUtil.addEncodedPaths(base.toURI().toASCIIString(),encoded));
            }
            else
            {
                uri=new URI(base.toURI().toASCIIString()+encoded);
            }
        }
        catch (final URISyntaxException e)
        {
            throw new InvalidPathException(base.toString() + childPath, e.getMessage())
            {
                {
                    initCause(e);
                }
            };
        }

        _uri=uri;
        _alias=checkFileAlias(_uri,_file);
    }
