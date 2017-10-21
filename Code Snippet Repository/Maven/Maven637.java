    @Override
    public MavenSession clone()
    {
        try
        {
            return (MavenSession) super.clone();
        }
        catch ( CloneNotSupportedException e )
        {
            throw new RuntimeException( "Bug", e );
        }
    }
