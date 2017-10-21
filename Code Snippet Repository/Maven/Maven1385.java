    public void setSource( Model source )
    {
        this.sourceModel = source;
        this.source = null;

        if ( rootModel == null )
        {
            rootModel = source;
        }
    }
