    public ResourceCollection(String[] resources)
    {
        _resources = new Resource[resources.length];
        try
        {
            for(int i=0; i<resources.length; i++)
            {
                _resources[i] = Resource.newResource(resources[i]);
                if(!_resources[i].exists() || !_resources[i].isDirectory())
                    throw new IllegalArgumentException(_resources[i] + " is not an existing directory.");
            }
        }
        catch(IllegalArgumentException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }
