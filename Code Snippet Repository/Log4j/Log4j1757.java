    @Test
    public void validateXmlSchema() throws Exception {
        final File file = new File("target", "XmlCompactFileAppenderValidationTest.log.xml");
        file.delete();
        final Logger log = LogManager.getLogger("com.foo.Bar");
        log.warn("Message 1");
        log.info("Message 2");
        log.debug("Message 3");
        Configurator.shutdown(this.loggerContext);
        this.validateXmlSchema(file);
    }
