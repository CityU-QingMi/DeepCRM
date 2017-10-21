    public void testUseCustomResultBuilder() throws Exception {
        // given
        initDispatcherWithConfigs("struts-default.xml,struts-object-factory-result-builder.xml");

        // when
        ResultFactory actual = container.getInstance(ResultFactory.class);

        // then
        assertTrue(actual instanceof MyResultFactory);
    }
