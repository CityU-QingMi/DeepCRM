    public void addBinding(AppLifeCycle.Binding binding)
    {
        for (String nodeName : binding.getBindingTargets())
        {
            List<Binding> bindings = lifecyclebindings.get(nodeName);
            if (bindings == null)
            {
                bindings = new ArrayList<Binding>();
            }
            bindings.add(binding);

            lifecyclebindings.put(nodeName,bindings);
        }
    }
