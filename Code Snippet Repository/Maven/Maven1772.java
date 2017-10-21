    public MojoDescriptor getMojo( String goal )
    {
        if ( getMojos() == null )
        {
            return null; // no mojo in this POM
        }

        // TODO could we use a map? Maybe if the parent did that for components too, as this is too vulnerable to
        // changes above not being propagated to the map
        for ( MojoDescriptor desc : getMojos() )
        {
            if ( goal.equals( desc.getGoal() ) )
            {
                return desc;
            }
        }
        return null;
    }
