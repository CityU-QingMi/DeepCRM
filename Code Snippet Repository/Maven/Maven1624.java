    protected void mergeNotifier_Address( Notifier target, Notifier source, boolean sourceDominant,
                                          Map<Object, Object> context )
    {
        String src = source.getAddress();
        if ( src != null )
        {
            if ( sourceDominant || target.getAddress() == null )
            {
                target.setAddress( src );
            }
        }
    }
