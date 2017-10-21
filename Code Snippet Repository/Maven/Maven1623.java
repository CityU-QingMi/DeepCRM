    protected void mergeNotifier_Type( Notifier target, Notifier source, boolean sourceDominant,
                                       Map<Object, Object> context )
    {
        String src = source.getType();
        if ( src != null )
        {
            if ( sourceDominant || target.getType() == null )
            {
                target.setType( src );
            }
        }
    }
