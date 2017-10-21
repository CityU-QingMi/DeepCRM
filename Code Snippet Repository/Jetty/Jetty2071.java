    public RangeAttrEventTrigger(String objectName, String attributeName,TYPE min, TYPE max)
        throws MalformedObjectNameException, IllegalArgumentException
    {
        super(objectName,attributeName);
        
        if (min == null)
            throw new IllegalArgumentException("Value cannot be null");
        if (max == null)
            throw new IllegalArgumentException("Value cannot be null");

        _min = min;
        _max = max;
    }
