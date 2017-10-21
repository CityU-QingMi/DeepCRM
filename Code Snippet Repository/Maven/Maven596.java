    @Override
    public MavenExecutionRequest setGoals( List<String> goals )
    {
        if ( goals != null )
        {
            this.goals = new ArrayList<>( goals );
        }
        else
        {
            this.goals = null;
        }

        return this;
    }
