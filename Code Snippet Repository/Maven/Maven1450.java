    @Override
    protected void mergeModel_Organization( Model target, Model source, boolean sourceDominant,
                                            Map<Object, Object> context )
    {
        Organization src = source.getOrganization();
        if ( src != null )
        {
            Organization tgt = target.getOrganization();
            if ( tgt == null )
            {
                tgt = new Organization();
                tgt.setLocation( "", src.getLocation( "" ) );
                target.setOrganization( tgt );
                mergeOrganization( tgt, src, sourceDominant, context );
            }
        }
    }
