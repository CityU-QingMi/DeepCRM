    @Override
    public boolean hasSubProtocol(String test)
    {
        for (String protocol : getSubProtocols())
        {
            if (protocol.equalsIgnoreCase(test))
            {
                return true;
            }
        }
        return false;
    }
