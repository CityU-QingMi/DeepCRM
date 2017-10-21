    public EqualToAttrEventTrigger(String objectName, String attributeName, TYPE value)
        throws MalformedObjectNameException, IllegalArgumentException
    {
        super(objectName,attributeName);
        
        if (value == null)
            throw new IllegalArgumentException("Value cannot be null");

        _value = value;
    }
