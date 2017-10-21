    public void addClassPath(Resource resource)
        throws IOException
    {
        if (resource instanceof ResourceCollection)
        {
            for (Resource r : ((ResourceCollection)resource).getResources())
                addClassPath(r);
        }
        else
        {
            addClassPath(resource.toString());
        }
    }
