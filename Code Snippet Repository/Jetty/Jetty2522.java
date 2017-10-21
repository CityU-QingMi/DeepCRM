    public void visitMetaInfResource (WebAppContext context, Resource dir)
    {
        Collection<Resource> metaInfResources =  (Collection<Resource>)context.getAttribute(MetaInfConfiguration.METAINF_RESOURCES);
        if (metaInfResources == null)
        {
            metaInfResources = new HashSet<Resource>();
            context.setAttribute(MetaInfConfiguration.METAINF_RESOURCES, metaInfResources);
        }
        metaInfResources.add(dir);
        //also add to base resource of webapp
        Resource[] collection=new Resource[metaInfResources.size()+1];
        int i=0;
        collection[i++]=context.getBaseResource();
        for (Resource resource : metaInfResources)
            collection[i++]=resource;
        context.setBaseResource(new ResourceCollection(collection));
    }
