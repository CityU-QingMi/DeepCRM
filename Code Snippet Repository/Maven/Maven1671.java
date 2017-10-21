    protected void mergeModel_Scm( Model target, Model source, boolean sourceDominant, Map<Object, Object> context )
    {
        Scm src = source.getScm();
        if ( src != null )
        {
            Scm tgt = target.getScm();
            if ( tgt == null )
            {
                tgt = new Scm();
                tgt.setTag( null );
                target.setScm( tgt );
            }
            mergeScm( tgt, src, sourceDominant, context );
        }
    }
