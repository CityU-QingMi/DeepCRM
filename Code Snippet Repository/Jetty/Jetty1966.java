    private static File toFile(Resource resource)
    {
        try
        {
            return resource.getFile();
        }
        catch ( IOException e )
        {
            throw new RuntimeException( e.getMessage(), e );
        }
    }
