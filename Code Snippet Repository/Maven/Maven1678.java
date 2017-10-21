    protected void mergeModelBase_Modules( ModelBase target, ModelBase source, boolean sourceDominant,
                                           Map<Object, Object> context )
    {
        List<String> src = source.getModules();
        if ( !src.isEmpty() )
        {
            List<String> tgt = target.getModules();
            List<String> merged = new ArrayList<>( tgt.size() + src.size() );
            merged.addAll( tgt );
            merged.addAll( src );
            target.setModules( merged );
        }
    }
