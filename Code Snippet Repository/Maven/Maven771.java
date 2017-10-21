    public void set( String goals )
    {
        mojos = new ArrayList<>();
        
        if ( StringUtils.isNotEmpty( goals ) )
        {
            String[] mojoGoals = StringUtils.split( goals, "," );
            
            for ( String mojoGoal: mojoGoals )
            {
                LifecycleMojo lifecycleMojo = new LifecycleMojo();
                lifecycleMojo.setGoal( mojoGoal.trim() );
                mojos.add( lifecycleMojo );
            }
        }
    }
