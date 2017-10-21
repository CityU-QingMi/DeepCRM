    public int hashCode()
    {
        int result = 1;

        String goal = getGoal();

        if ( goal != null )
        {
            result += goal.hashCode();
        }

        PluginDescriptor pd = getPluginDescriptor();

        if ( pd != null )
        {
            result -= pd.hashCode();
        }

        return result;
    }
