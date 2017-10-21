    protected void mergeMailingList_Post( MailingList target, MailingList source, boolean sourceDominant,
                                          Map<Object, Object> context )
    {
        String src = source.getPost();
        if ( src != null )
        {
            if ( sourceDominant || target.getPost() == null )
            {
                target.setPost( src );
                target.setLocation( "post", source.getLocation( "post" ) );
            }
        }
    }
