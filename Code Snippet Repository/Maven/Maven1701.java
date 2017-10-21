    protected void mergeRepository_Snapshots( Repository target, Repository source, boolean sourceDominant,
                                              Map<Object, Object> context )
    {
        RepositoryPolicy src = source.getSnapshots();
        if ( src != null )
        {
            RepositoryPolicy tgt = target.getSnapshots();
            if ( tgt == null )
            {
                tgt = new RepositoryPolicy();
                target.setSnapshots( tgt );
            }
            mergeRepositoryPolicy( tgt, src, sourceDominant, context );
        }
    }
