    @Override
    public boolean equals( Object obj )
    {
        if ( obj == null )
        {
            return false;
        }

        if ( MetadataGraphNode.class.isAssignableFrom( obj.getClass() ) )
        {
            MetadataGraphNode node2 = (MetadataGraphNode) obj;

            if ( node2.metadata == null )
            {
                return metadata == null;
            }

            return metadata != null && metadata.toString().equals( node2.metadata.toString() );
        }
        else
        {
            return super.equals( obj );
        }
    }
