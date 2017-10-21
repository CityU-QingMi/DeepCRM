    @Override
    public Requirement clone()
    {
        try
        {
            return (Requirement) super.clone();
        }
        catch ( CloneNotSupportedException e )
        {
            throw new UnsupportedOperationException( e );
        }
    }
