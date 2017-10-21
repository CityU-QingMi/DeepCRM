    public boolean matches(String host)
    {
        host=StringUtil.asciiToLowerCase(host);
        if (_hosts.contains(host) || _wilds.contains(host))
            return true;

        int dot = host.indexOf('.');
        if (dot>=0)
        {
            String domain=host.substring(dot+1);
            if (_wilds.contains(domain))
                return true;
        }
        return false;
    }
