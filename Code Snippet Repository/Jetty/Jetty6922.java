    public List<ExtensionConfig> parseExtensions(List<String> requestLines)
    {
        List<ExtensionConfig> extensionConfigs = new ArrayList<>();
        
        List<String> hits = regexFind(requestLines, "^Sec-WebSocket-Extensions: (.*)$");

        for (String econf : hits)
        {
            // found extensions
            ExtensionConfig config = ExtensionConfig.parse(econf);
            extensionConfigs.add(config);
        }

        return extensionConfigs;
    }
