    public AttrEventTrigger(ObjectName nameObject, String attributeName)
        throws IllegalArgumentException
    {
        if (nameObject == null)
            throw new IllegalArgumentException("Object name cannot be null");
        if (attributeName == null)
            throw new IllegalArgumentException("Attribute name cannot be null");
        
        _states =  new ConcurrentHashMap<Long,EventState<TYPE>>();
        
        _objectName = nameObject.toString();
        _attributeName = attributeName;
        
        _nameObject = nameObject;
    }
