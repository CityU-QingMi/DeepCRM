    @Override
    public String toString()
    {
        StringBuilder buffer = new StringBuilder( 128 );

        buffer.append( '[' ).append( getSeverity() ).append( "] " );
        buffer.append( getMessage() );
        buffer.append( " @ " ).append( ModelProblemUtils.formatLocation( this, null ) );

        return buffer.toString();
    }
