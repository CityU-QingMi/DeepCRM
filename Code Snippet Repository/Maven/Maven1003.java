    @Override
    public boolean equals( Object obj )
    {
        if ( obj == null )
        {
            return false;
        }

        if ( this == obj )
        {
            return true;
        }

        if ( !( obj instanceof DefaultToolchain ) )
        {
            return false;
        }

        DefaultToolchain other = (DefaultToolchain) obj;

        if ( type == null ? other.type != null : !type.equals( other.type ) )
        {
            return false;
        }

        Properties thisProvides = this.getModel().getProvides();
        Properties otherProvides = other.getModel().getProvides();

        if ( thisProvides == null ? otherProvides != null : !thisProvides.equals( otherProvides ) )
        {
            return false;
        }

        return true;
    }
