    @Test
    public void shouldGenerateClassWithAppropriateAccessorsForBoolean() throws Exception {
        //given
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("checkbox",true);
        Map<String, Class<?>> properties = new HashMap<String, Class<?>>();
        properties.put("checkbox", Boolean.class);

        //when
        Class testClass = MapProxyTool.classForName("TestClass3", properties, new ClassLoaderServiceImpl());
        Object testClassInstance = testClass.getConstructor(Map.class).newInstance(map);

        //then
        Getter getter = ReflectionTools.getGetter(testClass, "checkbox", "property", serviceRegistry);
        Assert.assertTrue((Boolean) getter.get(testClassInstance));
    }
