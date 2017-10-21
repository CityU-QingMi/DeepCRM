    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder( 512 );
        if ( isEmpty() )
        {
            return "empty";
        }
        for ( MetadataGraphVertex v : vertices )
        {
            sb.append( "Vertex:  " ).append( v.getMd().toString() ).append( '\n' );
            List<MetadataGraphEdge> ins = getIncidentEdges( v );
            if ( ins != null )
            {
                for ( MetadataGraphEdge e : ins )
                {
                    sb.append( "       from :  " ).append( e.toString() ).append( '\n' );
                }
            }
            else
            {
                sb.append( "      no entries\n" );
            }

            List<MetadataGraphEdge> outs = getExcidentEdges( v );
            if ( outs != null )
            {
                for ( MetadataGraphEdge e : outs )
                {
                    sb.append( "        to :  " ).append( e.toString() ).append( '\n' );
                }
            }
            else
            {
                sb.append( "      no exit\n" );
            }

            sb.append( "-------------------------------------------------\n" );
        }
        sb.append( "=============================================================\n" );
        return sb.toString();
    }
