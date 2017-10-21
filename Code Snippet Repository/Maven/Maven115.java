        public ResolveTask( ClassLoader classLoader, CountDownLatch latch, Artifact artifact,
                            RepositorySystemSession session, List<ArtifactRepository> remoteRepositories,
                            ArtifactResolutionResult result )
        {
            this.classLoader = classLoader;
            this.latch = latch;
            this.artifact = artifact;
            this.session = session;
            this.remoteRepositories = remoteRepositories;
            this.result = result;
        }
