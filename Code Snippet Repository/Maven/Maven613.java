    @Override
    public MavenExecutionRequest addMirror( Mirror mirror )
    {
        Validate.notNull( mirror, "mirror cannot be null" );

        for ( Mirror p : getMirrors() )
        {
            if ( p.getId() != null && p.getId().equals( mirror.getId() ) )
            {
                return this;
            }
        }

        getMirrors().add( mirror );

        return this;
    }
