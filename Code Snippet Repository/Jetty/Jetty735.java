    public Set<AppLifeCycle.Binding> getBindings()
    {
        Set<Binding> boundset = new HashSet<Binding>();

        for (List<Binding> bindings : lifecyclebindings.values())
        {
            boundset.addAll(bindings);
        }

        return boundset;
    }
