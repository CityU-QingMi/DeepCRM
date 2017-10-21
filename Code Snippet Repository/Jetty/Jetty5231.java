    @Override
    public Resource addPath(String path) throws IOException, MalformedURLException
    {
        if(_resources==null)
            throw new IllegalStateException("*resources* not set.");
        
        if(path==null)
            throw new MalformedURLException();
        
        if(path.length()==0 || URIUtil.SLASH.equals(path))
            return this;
        
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
                if (resources==null)
                    resources = new ArrayList<Resource>();
                    
                if (resource!=null)
                {
                    resources.add(resource);
                    resource=null;
                }
                
                resources.add(r);
            }
        }

        if (resource!=null)
            return resource;
        if (resources!=null)
            return new ResourceCollection(resources.toArray(new Resource[resources.size()]));
        return null;
    }
