    protected void mergeModel_ModelVersion( Model target, Model source, boolean sourceDominant,
                                            Map<Object, Object> context )
    {
        String src = source.getModelVersion();
        if ( src != null )
        {
            if ( sourceDominant || target.getModelVersion() == null )
            {
                target.setModelVersion( src );
                target.setLocation( "modelVersion", source.getLocation( "modelVersion" ) );
            }
        }
    }
