    protected void mergeModel_Prerequisites( Model target, Model source, boolean sourceDominant,
                                             Map<Object, Object> context )
    {
        Prerequisites src = source.getPrerequisites();
        if ( src != null )
        {
            Prerequisites tgt = target.getPrerequisites();
            if ( tgt == null )
            {
                tgt = new Prerequisites();
                tgt.setMaven( null );
                target.setPrerequisites( tgt );
            }
            mergePrerequisites( tgt, src, sourceDominant, context );
        }
    }
