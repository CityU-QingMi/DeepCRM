    public void addDependency(T dependent, T dependency)
    {
        Set<T> set = _dependencies.get(dependent);
        if (set==null)
        {
            set=new HashSet<>();
            _dependencies.put(dependent,set);
        }
        set.add(dependency);
    }
