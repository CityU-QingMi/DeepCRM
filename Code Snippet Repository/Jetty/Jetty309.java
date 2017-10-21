    @Override
    public Request path(String path)
    {
        URI uri = newURI(path);
        if (uri == null)
        {
            this.path = path;
            this.query = null;
        }
        else
        {
            String rawPath = uri.getRawPath();
            if (rawPath == null)
                rawPath = "";
            this.path = rawPath;
            String query = uri.getRawQuery();
            if (query != null)
            {
                this.query = query;
                params.clear();
                extractParams(query);
            }
            if (uri.isAbsolute())
                this.path = buildURI(false).toString();
        }
        this.uri = null;
        return this;
    }
