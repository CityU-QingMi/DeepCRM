    public void removeBinding (Name name)
    {
        String key = name.toString();
        if (__log.isDebugEnabled())
            __log.debug("Removing binding with key="+key);
        Binding binding = _bindings.remove(key);
        if (binding!=null)
        {
            Collection<Listener> list = findListeners();
            for (Listener listener : list)
                listener.unbind(this,binding);
        }
    }
