    private static List<Resource> getLayers(Layer... layers)
    {
        List<Resource> resources = new ArrayList<Resource>();
        for (Layer layer: layers)
        {
            if (layer==null)
                continue;
            Resource resource = layer.getBaseResource();
            if (resource.exists())
                resources.add(resource);
        }
        return resources;
    }
