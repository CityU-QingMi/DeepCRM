    @Override
    public String toString()
    {
        StringBuilder buffer = new StringBuilder( 128 );
        if ( mojoDescriptor != null )
        {
            buffer.append( mojoDescriptor.getId() );
        }
        buffer.append( " {execution: " ).append( executionId ).append( '}' );
        return buffer.toString();
    }
