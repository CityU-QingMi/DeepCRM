    public static void toArtifacts( Collection<org.apache.maven.artifact.Artifact> artifacts,
                                    Collection<? extends DependencyNode> nodes, List<String> trail,
                                    DependencyFilter filter )
    {
        for ( DependencyNode node : nodes )
        {
            org.apache.maven.artifact.Artifact artifact = toArtifact( node.getDependency() );

            List<String> nodeTrail = new ArrayList<>( trail.size() + 1 );
            nodeTrail.addAll( trail );
            nodeTrail.add( artifact.getId() );

            if ( filter == null || filter.accept( node, Collections.<DependencyNode>emptyList() ) )
            {
                artifact.setDependencyTrail( nodeTrail );
                artifacts.add( artifact );
            }

            toArtifacts( artifacts, node.getChildren(), nodeTrail, filter );
        }
    }
