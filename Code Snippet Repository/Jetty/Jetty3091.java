    public ResourceHandler()
    {
        this(new ResourceService()
        {
            @Override
            protected void notFound(HttpServletRequest request, HttpServletResponse response) throws IOException
            {
            }
        });
        _resourceService.setGzipEquivalentFileExtensions(new ArrayList<>(Arrays.asList(new String[] { ".svgz" })));
    }
