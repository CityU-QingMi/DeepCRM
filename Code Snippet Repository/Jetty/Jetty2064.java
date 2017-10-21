    public LessThanAttrEventTrigger(String objectName, String attributeName, TYPE max)
        throws MalformedObjectNameException, IllegalArgumentException
    {
        super(objectName,attributeName);
        
        if (max == null)
            throw new IllegalArgumentException("Value cannot be null");

        _max = max;
    }
