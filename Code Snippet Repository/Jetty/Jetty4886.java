    public <SET extends Set<T>> IncludeExcludeSet(Class<SET> setClass)
    {
        try
        {
            _includes = setClass.newInstance();
            _excludes = setClass.newInstance();
            
            if(_includes instanceof Predicate) 
            {
                _includePredicate = (Predicate<P>)_includes;
            } 
            else 
            {
                _includePredicate = new SetContainsPredicate(_includes);
            }
            
            if(_excludes instanceof Predicate) 
            {
                _excludePredicate = (Predicate<P>)_excludes;
            } 
            else 
            {
                _excludePredicate = new SetContainsPredicate(_excludes);
            }
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
    }
