        private Set<Artifact> createArtifacts( ArtifactFactory artifactFactory, Set<Artifact> dependencies, String inheritedScope,
                                     ArtifactFilter dependencyFilter )
            throws InvalidVersionSpecificationException
        {
            Set<Artifact> projectArtifacts = new HashSet<>();

            for ( Artifact d : dependencies )
            {
                VersionRange versionRange;
                if ( d.getVersionRange() != null )
                {
                    versionRange = d.getVersionRange();
                }
                else
                {
                    versionRange = VersionRange.createFromVersionSpec( d.getVersion() );
                }
                Artifact artifact;
                if ( d.getScope().equals( Artifact.SCOPE_TEST ) || d.getScope().equals( Artifact.SCOPE_PROVIDED ) )
                {
                    /* don't call createDependencyArtifact as it'll ignore test and provided scopes */
                    artifact =
                        artifactFactory.createArtifact( d.getGroupId(), d.getArtifactId(), d.getVersion(), d.getScope(),
                                                        d.getType() );
                }
                else
                {
                    artifact =
                        artifactFactory.createDependencyArtifact( d.getGroupId(), d.getArtifactId(), versionRange,
                                                                  d.getType(), d.getClassifier(), d.getScope(),
                                                                  inheritedScope, d.isOptional() );
                }

                if ( artifact != null && ( dependencyFilter == null || dependencyFilter.include( artifact ) ) )
                {
                    artifact.setDependencyFilter( dependencyFilter );

                    projectArtifacts.add( artifact );
                }
            }

            return projectArtifacts;
        }
