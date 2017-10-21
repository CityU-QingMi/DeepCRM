    public void setSession( MavenSession session )
    {
        AtomicReference<MavenSession> reference = DefaultLegacySupport.SESSION.get();
        if ( reference != null )
        {
            reference.set( null );
        }

        if ( session == null && reference != null )
        {
            DefaultLegacySupport.SESSION.remove();
        }
        else
        {
            DefaultLegacySupport.SESSION.set( new AtomicReference<>( session ) );
        }
    }
