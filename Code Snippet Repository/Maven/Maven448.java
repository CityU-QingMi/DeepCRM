        public ResolutionGroup retrieve( Artifact artifact, ArtifactRepository localRepository,
                                         List<ArtifactRepository> remoteRepositories )
            throws ArtifactMetadataRetrievalException
        {
            String key = getKey( artifact );

            ArtifactSpec a = (ArtifactSpec) artifacts.get( key );
            try
            {
                return new ResolutionGroup( artifact, createArtifacts( artifactFactory, a.dependencies,
                                                                       artifact.getScope(),
                                                                       artifact.getDependencyFilter() ),
                                            Collections.EMPTY_LIST );
            }
            catch ( InvalidVersionSpecificationException e )
            {
                throw new ArtifactMetadataRetrievalException( "Invalid version creating artifacts", e, artifact );
            }
        }
