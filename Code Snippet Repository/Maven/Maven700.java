    public boolean requiresProject( MavenSession session )
    {
        List<String> goals = session.getGoals();
        if ( goals != null )
        {
            for ( String goal : goals )
            {
                if ( !isGoalSpecification( goal ) )
                {
                    return true;
                }
            }
        }
        return false;
    }
