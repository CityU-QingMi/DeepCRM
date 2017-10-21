    public FileResource(URI uri)
    {
        File file=new File(uri);
        _file=file;
        try
        {
            URI file_uri = _file.toURI();
            _uri = normalizeURI(_file, uri);
            assertValidPath(file.toString());

            // Is it a URI alias?
            if (!URIUtil.equalsIgnoreEncodings(_uri.toASCIIString(), file_uri.toString()))
                _alias = _file.toURI();
            else
                _alias = checkFileAlias(_uri, _file);
        }
        catch (URISyntaxException e)
        {
            throw new InvalidPathException(_file.toString(), e.getMessage())
            {
                {
                    initCause(e);
                }
            };
        }
    }
