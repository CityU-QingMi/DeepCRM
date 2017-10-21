    @SuppressWarnings( "" )
    private void assembleDependencyManagementInheritance( Model child, Model parent )
    {
        DependencyManagement parentDepMgmt = parent.getDependencyManagement();

        DependencyManagement childDepMgmt = child.getDependencyManagement();

        if ( parentDepMgmt != null )
        {
            if ( childDepMgmt == null )
            {
                child.setDependencyManagement( parentDepMgmt );
            }
            else
            {
                List<Dependency> childDeps = childDepMgmt.getDependencies();

                Map<String, Dependency> mappedChildDeps = new TreeMap<>();
                for ( Dependency dep : childDeps )
                {
                    mappedChildDeps.put( dep.getManagementKey(), dep );
                }

                for ( Dependency dep : parentDepMgmt.getDependencies() )
                {
                    if ( !mappedChildDeps.containsKey( dep.getManagementKey() ) )
                    {
                        childDepMgmt.addDependency( dep );
                    }
                }
            }
        }
    }
