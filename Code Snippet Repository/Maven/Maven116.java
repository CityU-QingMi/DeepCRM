        public void run()
        {
            ClassLoader old = Thread.currentThread().getContextClassLoader();
            try
            {
                Thread.currentThread().setContextClassLoader( classLoader );
                resolve( artifact, remoteRepositories, session );
            }
            catch ( ArtifactNotFoundException anfe )
            {
                // These are cases where the artifact just isn't present in any of the remote repositories
                // because it wasn't deployed, or it was deployed in the wrong place.

                synchronized ( result )
                {
                    result.addMissingArtifact( artifact );
                }
            }
            catch ( ArtifactResolutionException e )
            {
                // This is really a wagon TransferFailedException so something went wrong after we successfully
                // retrieved the metadata.

                synchronized ( result )
                {
                    result.addErrorArtifactException( e );
                }
            }
            finally
            {
                latch.countDown();
                Thread.currentThread().setContextClassLoader( old );

            }
        }
