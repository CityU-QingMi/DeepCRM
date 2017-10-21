    public String getRequestPath()
    {
        StringBuilder path = new StringBuilder();
        path.append(destHttpURI.getPath());
        if (StringUtil.isNotBlank(destHttpURI.getQuery()))
        {
            path.append('?').append(destHttpURI.getRawQuery());
        }
        return path.toString();
    }
