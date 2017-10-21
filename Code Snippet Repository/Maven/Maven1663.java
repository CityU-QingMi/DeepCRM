    protected void mergeResource_MergeId( Resource target, Resource source, boolean sourceDominant,
                                          Map<Object, Object> context )
    {
        String src = source.getMergeId();
        if ( src != null )
        {
            if ( sourceDominant || target.getMergeId() == null )
            {
                target.setMergeId( src );
            }
        }
    }
