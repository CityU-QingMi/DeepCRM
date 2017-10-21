    public AttrEventTrigger(String objectName, String attributeName)
        throws MalformedObjectNameException, IllegalArgumentException
    {
        if (objectName == null)
            throw new IllegalArgumentException("Object name cannot be null");
        if (attributeName == null)
            throw new IllegalArgumentException("Attribute name cannot be null");
        
        _states =  new ConcurrentHashMap<Long,EventState<TYPE>>();
        
        _objectName = objectName;
        _attributeName = attributeName;
        
        _nameObject = new ObjectName(_objectName);
    }
