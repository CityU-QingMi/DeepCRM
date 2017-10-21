    public void setInjectedProfileIds( String source, List<String> injectedProfileIds )
    {
        if ( injectedProfileIds != null )
        {
            this.injectedProfileIds.put( source, new ArrayList<>( injectedProfileIds ) );
        }
        else
        {
            this.injectedProfileIds.remove( source );
        }
    }
