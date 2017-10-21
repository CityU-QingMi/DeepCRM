    public void expandDependencies(Props props)
    {
        Function<String,String> expander = d->{return props.expand(d);};
        
        List<String> tmp=_depends.stream().map(expander).collect(Collectors.toList());
        _depends.clear();
        _depends.addAll(tmp);
        tmp=_optional.stream().map(expander).collect(Collectors.toList());
        _optional.clear();
        _optional.addAll(tmp);
    }
