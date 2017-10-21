    private void write( File metadataFile, Metadata metadata )
        throws RepositoryException
    {
        metadataFile.getParentFile().mkdirs();
        try ( Writer writer = WriterFactory.newXmlWriter( metadataFile ) )
        {
            new MetadataXpp3Writer().write( writer, metadata );
        }
        catch ( IOException e )
        {
            throw new RepositoryException( "Could not write metadata " + metadataFile + ": " + e.getMessage(), e );
        }
    }
