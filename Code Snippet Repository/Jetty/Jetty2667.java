    @Override
    protected void doStart() throws Exception
    {
        Properties properties = new Properties();
        Resource resource = Resource.newResource(_config);
        properties.load(resource.getInputStream());

        _targetName = properties.getProperty("targetName");

        LOG.debug("Target Name {}", _targetName);

        super.doStart();
    }
