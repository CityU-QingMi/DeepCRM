    protected void mergeMailingList_Name( MailingList target, MailingList source, boolean sourceDominant,
                                          Map<Object, Object> context )
    {
        String src = source.getName();
        if ( src != null )
        {
            if ( sourceDominant || target.getName() == null )
            {
                target.setName( src );
                target.setLocation( "name", source.getLocation( "name" ) );
            }
        }
    }
