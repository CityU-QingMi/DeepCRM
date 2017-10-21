    public ResourceCollection(Resource... resources)
    {
        List<Resource> list = new ArrayList<Resource>();
        for (Resource r : resources)
        {
            if (r==null)
                continue;
            if (r instanceof ResourceCollection)
            {
                for (Resource r2 : ((ResourceCollection)r).getResources())
                    list.add(r2);
            }
            else
                list.add(r);
        }
        _resources = list.toArray(new Resource[list.size()]);
        for(Resource r : _resources)
        {
            if(!r.exists() || !r.isDirectory())
                throw new IllegalArgumentException(r + " is not an existing directory.");
        }
    }
