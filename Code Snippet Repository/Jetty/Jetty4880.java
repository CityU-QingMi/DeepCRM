    @Override
    public TYPE put(String addrSpec, TYPE object)
        throws IllegalArgumentException
    {
        if (addrSpec == null || addrSpec.trim().length() == 0)
            throw new IllegalArgumentException("Invalid IP address pattern: "+addrSpec);
        
        String spec = addrSpec.trim();
        if (_patterns.get(spec) == null)
            _patterns.put(spec,new IPAddrPattern(spec));
        
        return super.put(spec, object);
    }
