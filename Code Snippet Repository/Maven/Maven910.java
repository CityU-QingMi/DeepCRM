    @Override
    public MavenProject clone()
    {
        MavenProject clone;
        try
        {
            clone = (MavenProject) super.clone();
        }
        catch ( CloneNotSupportedException e )
        {
            throw new UnsupportedOperationException( e );
        }

        clone.deepCopy( this );

        return clone;
    }
