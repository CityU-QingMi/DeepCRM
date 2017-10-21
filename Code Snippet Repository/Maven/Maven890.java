    public void setInactiveProfileIds( List<String> inactiveProfileIds )
    {
        if ( inactiveProfileIds != null )
        {
            this.inactiveProfileIds = new ArrayList<>( inactiveProfileIds );
        }
        else
        {
            this.inactiveProfileIds.clear();
        }
    }
