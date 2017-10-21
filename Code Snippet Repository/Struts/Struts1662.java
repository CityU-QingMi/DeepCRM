    public void testCompositeActionMapperInstantiationWithList() throws Exception {
        // given
        initDispatcher(new HashMap<String, String>() {{
            put(StrutsConstants.STRUTS_I18N_ENCODING, "utf-8");
            put(StrutsConstants.STRUTS_MAPPER_COMPOSITE, "struts,restful");
        }});

        // when
        ActionMapper instance = container.getInstance(ActionMapper.class, "composite");

        // then
        assertNotNull(instance);
    }
