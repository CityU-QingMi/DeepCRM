    private List<Lifecycle> getOrderedLifecycles()
    {
        // NOTE: The lifecycle order can affect implied execution ids so we better be deterministic.

        List<Lifecycle> lifecycles = new ArrayList<>( defaultLifeCycles.getLifeCycles() );

        Collections.sort( lifecycles, new Comparator<Lifecycle>()
        {

            public int compare( Lifecycle l1, Lifecycle l2 )
            {
                return l1.getId().compareTo( l2.getId() );
            }

        } );

        return lifecycles;
    }
