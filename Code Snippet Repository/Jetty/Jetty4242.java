    protected boolean checkWhitelist(String candidate)
    {
        for (String address : _whitelist)
        {
            if (address.contains("/"))
            {
                if (subnetMatch(address, candidate))
                    return true;
            }
            else
            {
                if (address.equals(candidate))
                    return true;
            }
        }
        return false;
    }
