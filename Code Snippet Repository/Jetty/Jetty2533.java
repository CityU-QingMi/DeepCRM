    public void setStatusCode(int statusCode)
    {
        if ((300 <= statusCode) || (statusCode >= 399))
        {
            _statusCode = statusCode;
        }
        else
        {
            throw new IllegalArgumentException("Invalid redirect status code " + statusCode + " (must be a value between 300 and 399)");
        }
    }
