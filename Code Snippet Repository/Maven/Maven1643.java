    protected void mergeBuildBase_Filters( BuildBase target, BuildBase source, boolean sourceDominant,
                                           Map<Object, Object> context )
    {
        List<String> src = source.getFilters();
        if ( !src.isEmpty() )
        {
            List<String> tgt = target.getFilters();
            List<String> merged = new ArrayList<>( tgt.size() + src.size() );
            merged.addAll( tgt );
            merged.addAll( src );
            target.setFilters( merged );
        }
    }
