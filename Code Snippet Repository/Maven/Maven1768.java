    @Override
    public MojoDescriptor clone()
    {
        try
        {
            return (MojoDescriptor) super.clone();
        }
        catch ( CloneNotSupportedException e )
        {
            throw new UnsupportedOperationException( e );
        }
    }
