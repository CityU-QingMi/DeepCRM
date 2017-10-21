    public void merge( Model target, Model source, boolean sourceDominant, Map<?, ?> hints )
    {
        Validate.notNull( target, "target cannot be null" );

        if ( source == null )
        {
            return;
        }

        Map<Object, Object> context = new HashMap<>();
        if ( hints != null )
        {
            context.putAll( hints );
        }

        mergeModel( target, source, sourceDominant, context );
    }
