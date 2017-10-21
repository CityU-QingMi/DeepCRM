    public void populateResult( RepositorySystemSession session, ArtifactDescriptorResult result, Model model )
    {
        ArtifactTypeRegistry stereotypes = session.getArtifactTypeRegistry();

        for ( Repository r : model.getRepositories() )
        {
            result.addRepository( ArtifactDescriptorUtils.toRemoteRepository( r ) );
        }

        for ( org.apache.maven.model.Dependency dependency : model.getDependencies() )
        {
            result.addDependency( convert( dependency, stereotypes ) );
        }

        DependencyManagement mgmt = model.getDependencyManagement();
        if ( mgmt != null )
        {
            for ( org.apache.maven.model.Dependency dependency : mgmt.getDependencies() )
            {
                result.addManagedDependency( convert( dependency, stereotypes ) );
            }
        }

        Map<String, Object> properties = new LinkedHashMap<>();

        Prerequisites prerequisites = model.getPrerequisites();
        if ( prerequisites != null )
        {
            properties.put( "prerequisites.maven", prerequisites.getMaven() );
        }

        List<License> licenses = model.getLicenses();
        properties.put( "license.count", licenses.size() );
        for ( int i = 0; i < licenses.size(); i++ )
        {
            License license = licenses.get( i );
            properties.put( "license." + i + ".name", license.getName() );
            properties.put( "license." + i + ".url", license.getUrl() );
            properties.put( "license." + i + ".comments", license.getComments() );
            properties.put( "license." + i + ".distribution", license.getDistribution() );
        }

        result.setProperties( properties );

        setArtifactProperties( result, model );
    }
