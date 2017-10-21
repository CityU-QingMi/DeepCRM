    public String toString()
    {
        StringBuilder buf = new StringBuilder();

        buf.append( isLowerBoundInclusive() ? '[' : '(' );
        if ( getLowerBound() != null )
        {
            buf.append( getLowerBound().toString() );
        }
        buf.append( ',' );
        if ( getUpperBound() != null )
        {
            buf.append( getUpperBound().toString() );
        }
        buf.append( isUpperBoundInclusive() ? ']' : ')' );

        return buf.toString();
    }
