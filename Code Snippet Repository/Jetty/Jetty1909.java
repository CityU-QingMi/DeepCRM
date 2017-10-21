    public void addBinding (Name name, Object obj) throws NameAlreadyBoundException
    {
        String key = name.toString();
        Binding binding=new Binding (key, obj);

        Collection<Listener> list = findListeners();

        for (Listener listener : list)
        {
            binding=listener.bind(this,binding);
            if (binding==null)
                break;
        }

        if(__log.isDebugEnabled())
            __log.debug("Adding binding with key="+key+" obj="+obj+" for context="+_name+" as "+binding);

        if (binding!=null)
        {
            if (_bindings.containsKey(key)) {
                if(_supportDeepBinding) {
                    // quietly return (no exception)
                    // this is jndi spec breaking, but is added to support broken
                    // jndi users like openejb.
                    return;
                }
                throw new NameAlreadyBoundException(name.toString());
            }
            _bindings.put(key,binding);
        }
    }
