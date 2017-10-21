    public Set<AppLifeCycle.Binding> getBindings(String nodeName)
    {
        Set<Binding> boundset = new HashSet<Binding>();

        // Specific node binding
        List<Binding> bindings = lifecyclebindings.get(nodeName);
        if (bindings != null)
        {
            boundset.addAll(bindings);
        }

        // Special 'all nodes' binding
        bindings = lifecyclebindings.get(ALL_NODES);
        if (bindings != null)
        {
            boundset.addAll(bindings);
        }

        return boundset;
    }
