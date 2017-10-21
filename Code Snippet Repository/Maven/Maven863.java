    public void setCollectionErrors( List<Exception> exceptions )
    {
        if ( exceptions != null )
        {
            this.collectionErrors = exceptions;
        }
        else
        {
            this.collectionErrors = new ArrayList<>();
        }
    }
