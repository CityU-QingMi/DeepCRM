    @Override
    public boolean equals( Object other )
    {
        if ( this == other )
        {
            return true;
        }

        if ( !( other instanceof Restriction ) )
        {
            return false;
        }

        Restriction restriction = (Restriction) other;
        if ( lowerBound != null )
        {
            if ( !lowerBound.equals( restriction.lowerBound ) )
            {
                return false;
            }
        }
        else if ( restriction.lowerBound != null )
        {
            return false;
        }

        if ( lowerBoundInclusive != restriction.lowerBoundInclusive )
        {
            return false;
        }

        if ( upperBound != null )
        {
            if ( !upperBound.equals( restriction.upperBound ) )
            {
                return false;
            }
        }
        else if ( restriction.upperBound != null )
        {
            return false;
        }

        return upperBoundInclusive == restriction.upperBoundInclusive;

    }
