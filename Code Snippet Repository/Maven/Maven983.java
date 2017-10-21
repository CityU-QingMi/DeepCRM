    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + eventType;
        result = prime * result + ( ( exception == null ) ? 0 : exception.hashCode() );
        result = prime * result + ( ( localFile == null ) ? 0 : localFile.hashCode() );
        result = prime * result + requestType;
        return result;
    }
