    private URI buildURI(boolean withQuery)
    {
        String path = getPath();
        String query = getQuery();
        if (query != null && withQuery)
            path += "?" + query;
        URI result = newURI(path);
        if (result == null)
            return NULL_URI;
        if (!result.isAbsolute())
            result = URI.create(new Origin(getScheme(), getHost(), getPort()).asString() + path);
        return result;
    }
