    @Override
    public void setRequestURI(URI uri)
    {
        this.requestURI = uri;
        String scheme = uri.getScheme();
        if ("ws".equalsIgnoreCase(scheme))
        {
            secure = false;
        }
        else if ("wss".equalsIgnoreCase(scheme))
        {
            secure = true;
        }
        else
        {
            throw new IllegalArgumentException("URI scheme must be 'ws' or 'wss'");
        }
        this.host = this.requestURI.getHost();
        this.parameters.clear();
    }
