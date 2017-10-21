    @Override
    public void importManagement( Model target, List<? extends DependencyManagement> sources,
                                  ModelBuildingRequest request, ModelProblemCollector problems )
    {
        if ( sources != null && !sources.isEmpty() )
        {
            Map<String, Dependency> dependencies = new LinkedHashMap<>();

            DependencyManagement depMgmt = target.getDependencyManagement();

            if ( depMgmt != null )
            {
                for ( Dependency dependency : depMgmt.getDependencies() )
                {
                    dependencies.put( dependency.getManagementKey(), dependency );
                }
            }
            else
            {
                depMgmt = new DependencyManagement();
                target.setDependencyManagement( depMgmt );
            }

            for ( DependencyManagement source : sources )
            {
                for ( Dependency dependency : source.getDependencies() )
                {
                    String key = dependency.getManagementKey();
                    if ( !dependencies.containsKey( key ) )
                    {
                        dependencies.put( key, dependency );
                    }
                }
            }

            depMgmt.setDependencies( new ArrayList<>( dependencies.values() ) );
        }
    }
