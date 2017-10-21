    public String graphHash()
        throws MetadataResolutionException
    {
        if ( md == null )
        {
            throw new MetadataResolutionException( "treenode without metadata, parent: "
                + ( parent == null ? "null" : parent.toString() ) );
        }

        return md.groupId + ":" + md.artifactId;
    }
