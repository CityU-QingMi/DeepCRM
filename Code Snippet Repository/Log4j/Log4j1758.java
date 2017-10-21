    @Test
    public void validateXmlSchemaSimple() throws Exception {
        final File file = new File("target", "XmlCompactFileAsyncAppenderValidationTest.log.xml");
        file.delete();
        final Logger log = LogManager.getLogger("com.foo.Bar");
        log.warn("Message 1");
        log.info("Message 2");
        log.debug("Message 3");
        CoreLoggerContexts.stopLoggerContext(file); // stop async thread
        this.validateXmlSchema(file);
    }
