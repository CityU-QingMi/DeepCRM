    @Parameters(name = "")
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][]{
                        {"classpath:log4j-test1.xml", "target/test-xml.log"},
                        {"classpath:log4j-test1.json", "target/test-json.log"},
                        {"classpath:log4j-test1.yaml", "target/test-yaml.log"},
                        {"classpath:log4j-test1.properties", "target/test-properties.log"}
                }
        );
    }
