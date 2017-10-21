    public void setScopedVertices( boolean scopedVertices )
    {
        this.scopedVertices = scopedVertices;

        // scoped graph is versioned by definition
        if ( scopedVertices )
        {
            versionedVertices = true;
        }
    }
