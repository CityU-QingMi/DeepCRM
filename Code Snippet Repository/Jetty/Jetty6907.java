    private List<ExtensionConfig> getExtensionConfigs(HttpResponse response)
    {
        List<ExtensionConfig> configs = new ArrayList<>();

        String econf = response.getHeader("Sec-WebSocket-Extensions");
        if (econf != null)
        {
            LOG.debug("Found Extension Response: {}",econf);
            ExtensionConfig config = ExtensionConfig.parse(econf);
            configs.add(config);
        }
        return configs;
    }
