    @Override
    public boolean equals( Object other )
    {
        if ( other == this )
        {
            return true;
        }
        else if ( !( other instanceof MavenProject ) )
        {
            return false;
        }

        MavenProject that = (MavenProject) other;

        return eq( getArtifactId(), that.getArtifactId() ) && eq( getGroupId(), that.getGroupId() )
            && eq( getVersion(), that.getVersion() );
    }
