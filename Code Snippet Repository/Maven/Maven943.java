    private void addEdge( Map<String, MavenProject> projectMap, Map<String, Map<String, Vertex>> vertexMap,
                          MavenProject project, Vertex projectVertex, String groupId, String artifactId,
                          String version, boolean force, boolean safe )
        throws CycleDetectedException
    {
        String projectKey = ArtifactUtils.versionlessKey( groupId, artifactId );

        Map<String, Vertex> vertices = vertexMap.get( projectKey );

        if ( vertices != null )
        {
            if ( isSpecificVersion( version ) )
            {
                Vertex vertex = vertices.get( version );
                if ( vertex != null )
                {
                    addEdge( projectVertex, vertex, project, projectMap, force, safe );
                }
            }
            else
            {
                for ( Vertex vertex : vertices.values() )
                {
                    addEdge( projectVertex, vertex, project, projectMap, force, safe );
                }
            }
        }
    }
