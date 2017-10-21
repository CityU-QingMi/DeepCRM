        public boolean accept( DependencyNode node, List<DependencyNode> parents )
        {
            Dependency dependency = node.getDependency();
            if ( dependency != null )
            {
                org.eclipse.aether.artifact.Artifact a = dependency.getArtifact();
                String key = ArtifactUtils.key( a.getGroupId(), a.getArtifactId(), a.getVersion() );
                return !keys.contains( key );
            }
            return false;
        }
