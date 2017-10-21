    public boolean equals( Object obj )
    {
        if ( this == obj )
        {
            return true;
        }
        if ( ( obj == null ) || ( getClass() != obj.getClass() ) )
        {
            return false;
        }
        final ArtifactTransferEvent other = (ArtifactTransferEvent) obj;
        if ( eventType != other.eventType )
        {
            return false;
        }
        if ( exception == null )
        {
            if ( other.exception != null )
            {
                return false;
            }
        }
        else if ( !exception.getClass().equals( other.exception.getClass() ) )
        {
            return false;
        }
        if ( requestType != other.requestType )
        {
            return false;
        }
        else if ( !source.equals( other.source ) )
        {
            return false;
        }
        return true;
    }
