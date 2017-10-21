    public GreaterThanOrEqualToAttrEventTrigger(String objectName, String attributeName, TYPE min)
        throws MalformedObjectNameException, IllegalArgumentException
    {
        super(objectName,attributeName);
        
        if (min == null)
            throw new IllegalArgumentException("Value cannot be null");

        _min = min;
    }
