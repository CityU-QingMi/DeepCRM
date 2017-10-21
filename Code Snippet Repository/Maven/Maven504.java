    private LegacyLocalRepositoryManager( ArtifactRepository delegate )
    {
        this.delegate = Validate.notNull( delegate, "delegate cannot be null" );

        ArtifactRepositoryLayout layout = delegate.getLayout();
        repo =
            new LocalRepository( new File( delegate.getBasedir() ),
                                 ( layout != null ) ? layout.getClass().getSimpleName() : "legacy" );

/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
        realLocalRepo = ( layout instanceof DefaultRepositoryLayout ) && "local".equals( delegate.getId() );
    }
