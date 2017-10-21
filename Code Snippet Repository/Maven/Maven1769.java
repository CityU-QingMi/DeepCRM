    @Override
    public Parameter clone()
    {
        try
        {
            return (Parameter) super.clone();
        }
        catch ( CloneNotSupportedException e )
        {
            throw new UnsupportedOperationException( e );
        }
    }
