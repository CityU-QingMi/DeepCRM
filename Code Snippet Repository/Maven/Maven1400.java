    @Override
    public void assembleModelInheritance( Model child, Model parent, ModelBuildingRequest request,
                                          ModelProblemCollector problems )
    {
        Map<Object, Object> hints = new HashMap<>();
        String childPath = child.getProperties().getProperty( CHILD_DIRECTORY_PROPERTY, child.getArtifactId() );
        hints.put( CHILD_DIRECTORY, childPath );
        hints.put( MavenModelMerger.CHILD_PATH_ADJUSTMENT, getChildPathAdjustment( child, parent, childPath ) );
        merger.merge( child, parent, false, hints );
    }
