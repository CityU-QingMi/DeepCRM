    public <SET extends Set<T>> IncludeExcludeSet(Set<T> includeSet, Predicate<P> includePredicate, Set<T> excludeSet, Predicate<P> excludePredicate)
    {
        Objects.requireNonNull(includeSet,"Include Set");
        Objects.requireNonNull(includePredicate,"Include Predicate");
        Objects.requireNonNull(excludeSet,"Exclude Set");
        Objects.requireNonNull(excludePredicate,"Exclude Predicate");
        
        _includes = includeSet;
        _includePredicate = includePredicate;
        _excludes = excludeSet;
        _excludePredicate = excludePredicate;
    }
