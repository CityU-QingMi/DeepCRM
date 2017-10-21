    protected void mergeLicense_Comments( License target, License source, boolean sourceDominant,
                                          Map<Object, Object> context )
    {
        String src = source.getComments();
        if ( src != null )
        {
            if ( sourceDominant || target.getComments() == null )
            {
                target.setComments( src );
                target.setLocation( "comments", source.getLocation( "comments" ) );
            }
        }
    }
