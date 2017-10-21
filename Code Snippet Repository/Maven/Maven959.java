        public boolean isStale()
        {
            File pomFile = pomArtifact.getFile();
            if ( pomFile != null )
            {
                if ( pomFile.canRead() )
                {
                    return length != pomFile.length() || timestamp != pomFile.lastModified();
                }
                else
                {
                    // if the POM didn't exist, retry if any repo is configured to always update
                    boolean snapshot = pomArtifact.isSnapshot();
                    for ( ArtifactRepository repository : remoteRepositories )
                    {
                        ArtifactRepositoryPolicy policy =
                            snapshot ? repository.getSnapshots() : repository.getReleases();
                        if ( ArtifactRepositoryPolicy.UPDATE_POLICY_ALWAYS.equals( policy.getUpdatePolicy() ) )
                        {
                            return true;
                        }
                    }
                }
            }

            return length != -1 || timestamp != -1;
        }
