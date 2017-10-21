    protected void mergeMailingList_Archive( MailingList target, MailingList source, boolean sourceDominant,
                                             Map<Object, Object> context )
    {
        String src = source.getArchive();
        if ( src != null )
        {
            if ( sourceDominant || target.getArchive() == null )
            {
                target.setArchive( src );
                target.setLocation( "archive", source.getLocation( "archive" ) );
            }
        }
    }
