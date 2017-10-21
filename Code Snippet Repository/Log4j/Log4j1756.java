    @Test
    public void validateXmlSchemaThrowable() throws Exception {
        final File file = new File("target", "XmlCompactFileAppenderValidationTest.log.xml");
        file.delete();
        final Logger log = LogManager.getLogger("com.foo.Bar");
        try {
            throw new IllegalArgumentException("IAE");
        } catch (final IllegalArgumentException e) {
            log.warn("Message 1", e);
        }
        Configurator.shutdown(this.loggerContext);
        this.validateXmlSchema(file);
    }
