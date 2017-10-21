    @Override
    public boolean test(InetAddress address)
    {
        if (address==null)
            return false;
        byte[] raw = address.getAddress();
        for (InetPattern pattern : _patterns.values())
            if (pattern.test(address,raw))
                return true;
        return false;
    }
