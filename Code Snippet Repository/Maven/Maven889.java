    public void setActiveProfileIds( List<String> activeProfileIds )
    {
        if ( activeProfileIds != null )
        {
            this.activeProfileIds = new ArrayList<>( activeProfileIds );
        }
        else
        {
            this.activeProfileIds.clear();
        }
    }
