    @Test
    public void testFromJsonWithLog4jModule() throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        final boolean encodeThreadContextAsList = false;
        final SimpleModule module = new Log4jJsonModule(encodeThreadContextAsList, true, false);
        module.addDeserializer(StackTraceElement.class, new Log4jStackTraceElementDeserializer());
        mapper.registerModule(module);
        final StackTraceElement expected = new StackTraceElement("package.SomeClass", "someMethod", "SomeClass.java", 123);
        final String s = this.aposToQuotes("{'class':'package.SomeClass','method':'someMethod','file':'SomeClass.java','line':123}");
        final StackTraceElement actual = mapper.readValue(s, StackTraceElement.class);
        Assert.assertEquals(expected, actual);
    }
