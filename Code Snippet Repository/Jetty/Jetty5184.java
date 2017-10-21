    public FileResource(File file)
    {
        assertValidPath(file.toString());
        _file=file;
        try
        {
            _uri = normalizeURI(_file, _file.toURI());
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
        _alias=checkFileAlias(_uri,_file);
    }
