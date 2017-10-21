    public int hashCode()
    {
        int result = 17;

        result = 37 * result + getGroupId().hashCode();
        result = 37 * result + getArtifactId().hashCode();
        result = 37 * result + getType().hashCode();
        if ( getVersion() != null )
        {
            result = 37 * result + getVersion().hashCode();
        }
        result = 37 * result + ( getClassifier() != null ? getClassifier().hashCode() : 0 );

        return result;
    }
