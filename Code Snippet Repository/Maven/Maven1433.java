        public void mergeManagedDependencies( Model model )
        {
            DependencyManagement dependencyManagement = model.getDependencyManagement();
            if ( dependencyManagement != null )
            {
                Map<Object, Dependency> dependencies = new HashMap<>();
                Map<Object, Object> context = Collections.emptyMap();

                for ( Dependency dependency : model.getDependencies() )
                {
                    Object key = getDependencyKey( dependency );
                    dependencies.put( key, dependency );
                }

                for ( Dependency managedDependency : dependencyManagement.getDependencies() )
                {
                    Object key = getDependencyKey( managedDependency );
                    Dependency dependency = dependencies.get( key );
                    if ( dependency != null )
                    {
                        mergeDependency( dependency, managedDependency, false, context );
                    }
                }
            }
        }
