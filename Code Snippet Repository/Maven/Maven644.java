    private void blackList( String id )
    {
        if ( !blackList.contains( id ) )
        {
            blackList.add( id );

            List<String> dependents = sorter.getDependents( id );

            if ( dependents != null && !dependents.isEmpty() )
            {
                for ( String dependentId : dependents )
                {
                    if ( !buildSuccessesByProject.containsKey( dependentId ) && !buildFailuresByProject.containsKey(
                        dependentId ) )
                    {
                        blackList( dependentId );
                    }
                }
            }
        }
    }
