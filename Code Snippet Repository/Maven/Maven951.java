    public boolean equals( Object o )
    {
        if ( o == this )
        {
            return true;
        }

        if ( !( o instanceof Artifact ) )
        {
            return false;
        }

        Artifact a = (Artifact) o;

        if ( !a.getGroupId().equals( getGroupId() ) )
        {
            return false;
        }
        else if ( !a.getArtifactId().equals( getArtifactId() ) )
        {
            return false;
        }
        else if ( !a.getVersion().equals( getVersion() ) )
        {
            return false;
        }
        else if ( !a.getType().equals( getType() ) )
        {
            return false;
        }
        else if ( a.getClassifier() == null ? getClassifier() != null : !a.getClassifier().equals( getClassifier() ) )
        {
            return false;
        }

        return true;
    }
