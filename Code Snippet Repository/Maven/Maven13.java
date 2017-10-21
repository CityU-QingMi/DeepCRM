    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        if ( getGroupId() != null )
        {
            sb.append( getGroupId() );
            sb.append( ':' );
        }
        appendArtifactTypeClassifierString( sb );
        sb.append( ':' );
        if ( getBaseVersionInternal() != null )
        {
            sb.append( getBaseVersionInternal() );
        }
        else
        {
            sb.append( versionRange.toString() );
        }
        if ( scope != null )
        {
            sb.append( ':' );
            sb.append( scope );
        }
        return sb.toString();
    }
