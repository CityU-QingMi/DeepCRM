    static String toId( Model model )
    {
        if ( model == null )
        {
            return "";
        }

        String groupId = model.getGroupId();
        if ( groupId == null && model.getParent() != null )
        {
            groupId = model.getParent().getGroupId();
        }

        String artifactId = model.getArtifactId();

        String version = model.getVersion();
        if ( version == null )
        {
            version = "[unknown-version]";
        }

        return toId( groupId, artifactId, version );
    }
