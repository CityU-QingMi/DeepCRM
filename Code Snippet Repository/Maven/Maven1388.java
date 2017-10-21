    @Override
    public ModelSource2 getRelatedSource( String relPath )
    {
        relPath = relPath.replace( '\\', File.separatorChar ).replace( '/', File.separatorChar );

        File relatedPom = new File( getFile().getParentFile(), relPath );

        if ( relatedPom.isDirectory() )
        {
            // TODO figure out how to reuse ModelLocator.locatePom(File) here
            relatedPom = new File( relatedPom, "pom.xml" );
        }

        if ( relatedPom.isFile() && relatedPom.canRead() )
        {
            return new FileModelSource( new File( relatedPom.toURI().normalize() ) );
        }

        return null;
    }
