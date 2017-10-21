    protected boolean isTransportSecure()
    {
        switch (transport)
        {
            case HTTP:
            case H2C:
            case FCGI:
                return false;
            case HTTPS:
            case H2:
                return true;
            default:
                throw new IllegalArgumentException();
        }
    }
