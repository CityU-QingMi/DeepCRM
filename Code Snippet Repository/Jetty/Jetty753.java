    public void addBindings(AppLifeCycle.Binding[] bindings)
    {
        if ( _orderedBindings == null )
        {
           _orderedBindings = new LinkedList<AppLifeCycle.Binding>();
        }
        
        for (AppLifeCycle.Binding binding : bindings)
        {
            _orderedBindings.add(binding);
        }
    }
