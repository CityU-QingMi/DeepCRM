    protected void mergePatternSet_Includes( PatternSet target, PatternSet source, boolean sourceDominant,
                                             Map<Object, Object> context )
    {
        List<String> src = source.getIncludes();
        if ( !src.isEmpty() )
        {
            List<String> tgt = target.getIncludes();
            List<String> merged = new ArrayList<>( tgt.size() + src.size() );
            merged.addAll( tgt );
            merged.addAll( src );
            target.setIncludes( merged );
        }
    }
