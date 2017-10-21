    public void setResourcesAsCSV(String csvResources)
    {
        StringTokenizer tokenizer = new StringTokenizer(csvResources, ",;");
        int len = tokenizer.countTokens();
        if(len==0)
        {
            throw new IllegalArgumentException("ResourceCollection@setResourcesAsCSV(String) " +
                    " argument must be a string containing one or more comma-separated resource strings.");
        }
        
        List<Resource> resources = new ArrayList<>();
        
        try
        {            
            while(tokenizer.hasMoreTokens())
            {
                Resource resource = Resource.newResource(tokenizer.nextToken().trim());
                if(!resource.exists() || !resource.isDirectory())
                    LOG.warn(" !exist "+resource);
                else
                    resources.add(resource);
            }
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }

        _resources = resources.toArray(new Resource[resources.size()]);
    }
