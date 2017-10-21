    public static void mergeFilterLists( List<String> childFilters, List<String> parentFilters )
    {
        for ( String f : parentFilters )
        {
            if ( !childFilters.contains( f ) )
            {
                childFilters.add( f );
            }
        }
    }
