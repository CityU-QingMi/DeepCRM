    public synchronized void addDiscoveredAnnotation (DiscoveredAnnotation annotation)
    {
        if (annotation == null)
            return;
        
        //if no resource associated with an annotation or the resource is not one of the WEB-INF/lib jars,
        //map it to empty resource
        Resource resource = annotation.getResource();
        if (resource == null ||  !_webInfJars.contains(resource))
            resource = EmptyResource.INSTANCE;

        List<DiscoveredAnnotation> list = _annotations.get(resource);
        if (list == null)
        {
            list = new ArrayList<DiscoveredAnnotation>();
            _annotations.put(resource, list);
        }
        list.add(annotation);           
    }
