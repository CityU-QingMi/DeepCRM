    @Override
    @Deprecated
    public Wagon getWagon( String protocol )
        throws UnsupportedProtocolException
    {
        if ( protocol == null )
        {
            throw new UnsupportedProtocolException( "Unspecified protocol" );
        }

        String hint = protocol.toLowerCase( java.util.Locale.ENGLISH );

        Wagon wagon;
        try
        {
            wagon = container.lookup( Wagon.class, hint );
        }
        catch ( ComponentLookupException e )
        {
            throw new UnsupportedProtocolException( "Cannot find wagon which supports the requested protocol: "
                                                        + protocol, e );

        }

        return wagon;
    }
