    protected Object findResource(String path) throws IOException, MalformedURLException
    {        
        Resource resource=null;
        ArrayList<Resource> resources = null;
        int i=0;
        for(; i<_resources.length; i++)
        {
            resource = _resources[i].addPath(path);  
            if (resource.exists())
            {
                if (resource.isDirectory())
                    break;
               
                return resource;
            }
        }  

        for(i++; i<_resources.length; i++)
        {
            Resource r = _resources[i].addPath(path); 
            if (r.exists() && r.isDirectory())
            {
                if (resource!=null)
                {
                    resources = new ArrayList<Resource>();
                    resources.add(resource);
                }
                resources.add(r);
            }
        }
        
        if (resource!=null)
            return resource;
        if (resources!=null)
            return resources;
        return null;
    }
