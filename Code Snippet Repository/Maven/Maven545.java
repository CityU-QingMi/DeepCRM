    public boolean include( Artifact artifact )
    {
        String id = artifact.getGroupId() + ":" + artifact.getArtifactId();

        boolean matched = false;
        for ( Iterator<String> i = patterns.iterator(); i.hasNext() & !matched; )
        {
            // TODO what about wildcards? Just specifying groups? versions?
            if ( id.equals( i.next() ) )
            {
                matched = true;
            }
        }
        return matched;
    }
