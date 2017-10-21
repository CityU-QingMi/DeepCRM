    public List<Module> getEnabled()
    {
        List<Module> enabled = _modules.stream().filter(m->{return m.isEnabled();}).collect(Collectors.toList());

        TopologicalSort<Module> sort = new TopologicalSort<>();
        for (Module module: enabled)
        {
            Consumer<String> add = name ->
            {
                Module dependency = _names.get(name);
                if (dependency!=null && dependency.isEnabled())
                    sort.addDependency(module,dependency);
                
                Set<Module> provided = _provided.get(name);
                if (provided!=null)
                    for (Module p : provided)
                        if (p.isEnabled())
                            sort.addDependency(module,p);
            };
            module.getDepends().forEach(add);
            module.getOptional().forEach(add);
        }

        sort.sort(enabled);
        return enabled;
    }
