    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        List<LifecycleMojo> mojos = getMojos();
        if ( mojos != null )
        {
            for ( LifecycleMojo mojo: mojos )
            {
                if ( first )
                {
                    first = false;
                }
                else
                {
                    sb.append( ',' );
                }
                sb.append( mojo.getGoal() );
            }
        }
        return sb.toString();
    }
