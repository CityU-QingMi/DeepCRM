    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        if ( artifacts != null )
        {
            int i = 1;
            sb.append( "---------\n" );
            sb.append( artifacts.size() ).append( '\n' );
            for ( Artifact a : artifacts )
            {
                sb.append( i ).append( ' ' ).append( a ).append( '\n' );
                i++;
            }
            sb.append( "---------\n" );
        }

        return sb.toString();
    }
