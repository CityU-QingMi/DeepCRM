    protected void save (Object object)
    throws NamingException
    {
        __log.debug("SAVE {} in {}",this,_scope);
        InitialContext ic = new InitialContext();
        NameParser parser = ic.getNameParser("");
        Name prefix = NamingEntryUtil.getNameForScope(_scope);
      
        //bind the NamingEntry into the context
        Name namingEntryName = NamingEntryUtil.makeNamingEntryName(parser, getJndiName());
        namingEntryName.addAll(0, prefix);
        _namingEntryNameString = namingEntryName.toString();
        NamingUtil.bind(ic, _namingEntryNameString, this);
                
        //bind the object as well
        Name objectName = parser.parse(getJndiName());
        objectName.addAll(0, prefix);
        _objectNameString = objectName.toString();
        NamingUtil.bind(ic, _objectNameString, object);
    } 
