    public String toString()
    {
        if ( artifacts == null )
            return "";

        StringBuilder sb = new StringBuilder( 256 );
        int i = 1;
        sb.append( "---------\n" );
        sb.append( artifacts.size() ).append( '\n' );
        for ( Artifact a : artifacts )
        {
            sb.append( i ).append( ' ' ).append( a ).append( '\n' );
            i++;
        }
        sb.append( "---------\n" );
        return sb.toString();
    }
