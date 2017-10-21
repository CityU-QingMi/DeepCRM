    public ManagedAttributeListener(ContextHandler context,String... managedAttributes)
    {
        _context=context;

        for (String attr:managedAttributes)
            _managedAttributes.add(attr);
        
        if (LOG.isDebugEnabled())
            LOG.debug("managedAttributes {}",_managedAttributes);
    }
