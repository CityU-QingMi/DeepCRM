    private void visit(T item, Set<T> visited, List<T> sorted,Comparator<T> comparator)
    {
        // If the item has not been visited
        if(!visited.contains(item))
        {
            // We are visiting it now, so add it to the visited set
            visited.add(item);

            // Lookup the items dependencies
            Set<T> dependencies = _dependencies.get(item);
            if (dependencies!=null)
            {
                // Sort the dependencies 
                SortedSet<T> ordered_deps = new TreeSet<>(comparator);
                ordered_deps.addAll(dependencies);
                
                // recursively visit each dependency
                try
                {
                    for (T d:ordered_deps)
                        visit(d,visited,sorted,comparator);
                }
                catch (CyclicException e)
                {
                    throw new CyclicException(item,e);
                }
            }
            
            // Now that we have visited all our dependencies, they and their 
            // dependencies will have been added to the sorted list. So we can
            // now add the current item and it will be after its dependencies
            sorted.add(item);
        }
        else if (!sorted.contains(item))
            // If we have already visited an item, but it has not yet been put in the
            // sorted list, then we must be in a cycle!
            throw new CyclicException(item);
    }
