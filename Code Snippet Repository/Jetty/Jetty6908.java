    public String getRequestHost()
    {
        if (destHttpURI.getPort() > 0)
        {
            return String.format("%s:%d",destHttpURI.getHost(),destHttpURI.getPort());
        }
        else
        {
            return destHttpURI.getHost();
        }
    }
