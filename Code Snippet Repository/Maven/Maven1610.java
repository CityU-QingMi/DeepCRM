    protected void mergeContributor_Roles( Contributor target, Contributor source, boolean sourceDominant,
                                           Map<Object, Object> context )
    {
        List<String> src = source.getRoles();
        if ( !src.isEmpty() )
        {
            List<String> tgt = target.getRoles();
            List<String> merged = new ArrayList<>( tgt.size() + src.size() );
            merged.addAll( tgt );
            merged.addAll( src );
            target.setRoles( merged );
        }
    }
