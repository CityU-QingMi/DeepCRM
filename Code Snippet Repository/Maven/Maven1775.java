    @Override
    public PluginDescriptor clone()
    {
        try
        {
            return (PluginDescriptor) super.clone();
        }
        catch ( CloneNotSupportedException e )
        {
            throw new UnsupportedOperationException( e );
        }
    }
