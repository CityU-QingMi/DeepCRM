    public boolean isRedirect(Response response)
    {
        switch (response.getStatus())
        {
            case 301:
            case 302:
            case 303:
            case 307:
            case 308:
                return true;
            default:
                return false;
        }
    }
