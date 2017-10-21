    private static List<Resource> getLayeredResources(String path, Layer... layers)
    {
        List<Resource> resources = new ArrayList<Resource>();
        for (Layer layer: layers)
        {
            if (layer==null)
                continue;
            Resource resource = layer.getResource(path);
            if (resource.exists())
                resources.add(resource);
        }
        return resources;
    }
