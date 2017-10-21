    public boolean equals( Object object )
    {
        if ( this == object )
        {
            return true;
        }

        if ( object instanceof MojoDescriptor )
        {
            MojoDescriptor other = (MojoDescriptor) object;

            if ( !compareObjects( getPluginDescriptor(), other.getPluginDescriptor() ) )
            {
                return false;
            }

            return compareObjects( getGoal(), other.getGoal() );

        }

        return false;
    }
