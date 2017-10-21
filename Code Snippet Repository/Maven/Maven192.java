    @Override
    public int hashCode()
    {
        if ( metadata == null )
        {
            return super.hashCode();
        }

        return metadata.toString().hashCode();
    }
