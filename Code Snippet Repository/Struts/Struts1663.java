    public void testCompositeActionMapperInstantiationWithoutList() throws Exception {
        // given
        initDispatcher(new HashMap<String, String>() {{
            put(StrutsConstants.STRUTS_I18N_ENCODING, "utf-8");
        }});

        // when
        try {
            container.getInstance(ActionMapper.class, "composite");
            fail();
        }catch (Exception e) {
            // then
            // You cannot use CompositeActionMapper without defined list of "struts.mapper.composite"
            assertTrue(e.getMessage().contains("No mapping found for dependency [type=java.lang.String, name='struts.mapper.composite']"));
        }
    }
