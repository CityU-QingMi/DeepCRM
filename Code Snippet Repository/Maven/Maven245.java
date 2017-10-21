    public ConflictResolver getConflictResolver( String type )
        throws ConflictResolverNotFoundException
    {
        try
        {
            return (ConflictResolver) container.lookup( ConflictResolver.ROLE, type );
        }
        catch ( ComponentLookupException exception )
        {
            throw new ConflictResolverNotFoundException( "Cannot find conflict resolver of type: " + type );
        }
    }
