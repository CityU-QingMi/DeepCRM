    private void initLifecycleMap()
    {
        if ( lifecycleMap == null )
        {
            lifecycleMap = new HashMap<>();

            if ( lifecycles != null )
            {
                for ( Lifecycle lifecycle : lifecycles )
                {
                    lifecycleMap.put( lifecycle.getId(), lifecycle );
                }
            }
            else
            {
/**/
/**/
/**/
/**/

                String[] lifecycleIds = { "default", "clean", "site" };

                for ( String lifecycleId : lifecycleIds )
                {
                    Map<String, LifecyclePhase> phases = getLifecyclePhases( lifecycleId );
                    if ( phases != null )
                    {
                        Lifecycle lifecycle = new Lifecycle();

                        lifecycle.setId( lifecycleId );
                        lifecycle.setLifecyclePhases( phases );

                        lifecycleMap.put( lifecycleId, lifecycle );
                    }
                }
            }
        }
    }
