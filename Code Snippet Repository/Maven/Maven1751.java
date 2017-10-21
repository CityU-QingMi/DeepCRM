    protected void mergeMailingList_OtherArchives( MailingList target, MailingList source, boolean sourceDominant,
                                                   Map<Object, Object> context )
    {
        List<String> src = source.getOtherArchives();
        if ( !src.isEmpty() )
        {
            List<String> tgt = target.getOtherArchives();
            List<String> merged = new ArrayList<>( tgt.size() + src.size() );
            merged.addAll( tgt );
            merged.addAll( src );
            target.setOtherArchives( merged );
        }
    }
