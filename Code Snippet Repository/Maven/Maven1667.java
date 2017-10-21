    protected void mergePatternSet_Excludes( PatternSet target, PatternSet source, boolean sourceDominant,
                                             Map<Object, Object> context )
    {
        List<String> src = source.getExcludes();
        if ( !src.isEmpty() )
        {
            List<String> tgt = target.getExcludes();
            List<String> merged = new ArrayList<>( tgt.size() + src.size() );
            merged.addAll( tgt );
            merged.addAll( src );
            target.setExcludes( merged );
        }
    }
