    public ArtifactDescriptorResult readArtifactDescriptor( RepositorySystemSession session,
                                                            ArtifactDescriptorRequest request )
        throws ArtifactDescriptorException
    {
        ArtifactDescriptorResult result = new ArtifactDescriptorResult( request );

        Model model = loadPom( session, request, result );
        if ( model != null )
        {
            Map<String, Object> config = session.getConfigProperties();
            ArtifactDescriptorReaderDelegate delegate =
                (ArtifactDescriptorReaderDelegate) config.get( ArtifactDescriptorReaderDelegate.class.getName() );

            if ( delegate == null )
            {
                delegate = new ArtifactDescriptorReaderDelegate();
            }

            delegate.populateResult( session, result, model );
        }

        return result;
    }
