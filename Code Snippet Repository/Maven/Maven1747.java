    protected void mergeMailingList_Unsubscribe( MailingList target, MailingList source, boolean sourceDominant,
                                                 Map<Object, Object> context )
    {
        String src = source.getUnsubscribe();
        if ( src != null )
        {
            if ( sourceDominant || target.getUnsubscribe() == null )
            {
                target.setUnsubscribe( src );
                target.setLocation( "unsubscribe", source.getLocation( "unsubscribe" ) );
            }
        }
    }
