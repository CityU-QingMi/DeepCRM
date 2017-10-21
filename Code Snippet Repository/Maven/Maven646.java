    @Override
    public String getLocalRepository()
    {
        if ( request.getLocalRepositoryPath() != null )
        {
            return request.getLocalRepositoryPath().getAbsolutePath();
        }

        return null;
    }
