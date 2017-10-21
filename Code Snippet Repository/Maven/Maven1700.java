    protected void mergeRepository_Releases( Repository target, Repository source, boolean sourceDominant,
                                             Map<Object, Object> context )
    {
        RepositoryPolicy src = source.getReleases();
        if ( src != null )
        {
            RepositoryPolicy tgt = target.getReleases();
            if ( tgt == null )
            {
                tgt = new RepositoryPolicy();
                target.setReleases( tgt );
            }
            mergeRepositoryPolicy( tgt, src, sourceDominant, context );
        }
    }
