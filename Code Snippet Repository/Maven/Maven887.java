    public ProjectBuildingRequest setSystemProperties( Properties systemProperties )
    {
        if ( systemProperties != null )
        {
            this.systemProperties = SystemProperties.copyProperties( systemProperties );
        }
        else
        {
            this.systemProperties.clear();
        }

        return this;
    }
