    private static int artifactHashCode( Artifact a )
    {
        int result = 17;
        result = 31 * result + a.getGroupId().hashCode();
        result = 31 * result + a.getArtifactId().hashCode();
        result = 31 * result + a.getType().hashCode();
        if ( a.getVersion() != null )
        {
            result = 31 * result + a.getVersion().hashCode();
        }
        result = 31 * result + ( a.getClassifier() != null ? a.getClassifier().hashCode() : 0 );
        result = 31 * result + ( a.getScope() != null ? a.getScope().hashCode() : 0 );
        result = 31 * result + ( a.getDependencyFilter() != null ? a.getDependencyFilter().hashCode() : 0 );
        result = 31 * result + ( a.isOptional() ? 1 : 0 );
        return result;
    }
