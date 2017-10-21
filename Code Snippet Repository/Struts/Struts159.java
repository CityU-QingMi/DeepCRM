    public XmlConfigurationProvider(String filename, boolean errorIfMissing) {
        this.configFileName = filename;
        this.errorIfMissing = errorIfMissing;

        Map<String, String> mappings = new HashMap<>();
        mappings.put("-//Apache Struts//XWork 2.5//EN", "xwork-2.5.dtd");
        mappings.put("-//Apache Struts//XWork 2.3//EN", "xwork-2.3.dtd");
        mappings.put("-//Apache Struts//XWork 2.1.3//EN", "xwork-2.1.3.dtd");
        mappings.put("-//Apache Struts//XWork 2.1//EN", "xwork-2.1.dtd");
        mappings.put("-//Apache Struts//XWork 2.0//EN", "xwork-2.0.dtd");
        mappings.put("-//Apache Struts//XWork 1.1.1//EN", "xwork-1.1.1.dtd");
        mappings.put("-//Apache Struts//XWork 1.1//EN", "xwork-1.1.dtd");
        mappings.put("-//Apache Struts//XWork 1.0//EN", "xwork-1.0.dtd");
        setDtdMappings(mappings);
    }
