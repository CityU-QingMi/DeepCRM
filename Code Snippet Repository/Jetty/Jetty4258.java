    private ConfiguredHeader parseHeaderConfiguration(String config)
    {
        String[] config_tokens = config.trim().split(" ",2);
        String method = config_tokens[0].trim();
        String header = config_tokens[1];
        String[] header_tokens = header.trim().split(":",2);
        String header_name = header_tokens[0].trim();
        String header_value = header_tokens[1].trim();
        ConfiguredHeader configured_header = new ConfiguredHeader(header_name,header_value,method.startsWith("add"),method.endsWith("Date"));
        return configured_header;
    }
