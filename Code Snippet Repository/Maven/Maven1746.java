    protected void mergeMailingList_Subscribe( MailingList target, MailingList source, boolean sourceDominant,
                                               Map<Object, Object> context )
    {
        String src = source.getSubscribe();
        if ( src != null )
        {
            if ( sourceDominant || target.getSubscribe() == null )
            {
                target.setSubscribe( src );
                target.setLocation( "subscribe", source.getLocation( "subscribe" ) );
            }
        }
    }
