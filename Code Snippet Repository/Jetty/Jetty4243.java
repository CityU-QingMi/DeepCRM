    @Deprecated
    protected boolean checkWhitelist(List<String> whitelist, String candidate)
    {
        for (String address : whitelist)
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
