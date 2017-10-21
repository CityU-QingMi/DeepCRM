    @Test
    public void testStackTraceElementWithCustom() throws Exception {
        // first, via bean that contains StackTraceElement
        final StackTraceBean bean = MAPPER
                .readValue(
                        aposToQuotes("{'Location':{'class':'package.SomeClass','method':'someMethod','file':'SomeClass.java','line':13}}"),
                        StackTraceBean.class);
        Assert.assertNotNull(bean);
        Assert.assertNotNull(bean.location);
        Assert.assertEquals(StackTraceBean.NUM, bean.location.getLineNumber());

        // and then directly, iff registered
        final ObjectMapper mapper = new ObjectMapper();
        final SimpleModule module = new SimpleModule();
        module.addDeserializer(StackTraceElement.class, new MyStackTraceElementDeserializer());
        mapper.registerModule(module);

        final StackTraceElement elem = mapper.readValue(
                aposToQuotes("{'class':'package.SomeClass','method':'someMethod','file':'SomeClass.java','line':13}"),
                StackTraceElement.class);
        Assert.assertNotNull(elem);
        Assert.assertEquals(StackTraceBean.NUM, elem.getLineNumber());
    }
