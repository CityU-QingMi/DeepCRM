    protected void mergeModel_Packaging( Model target, Model source, boolean sourceDominant,
                                         Map<Object, Object> context )
    {
        String src = source.getPackaging();
        if ( src != null )
        {
            if ( sourceDominant || target.getPackaging() == null )
            {
                target.setPackaging( src );
                target.setLocation( "packaging", source.getLocation( "packaging" ) );
            }
        }
    }
