    @Test
    public void shouldGenerateClassWithAppropriateGetter() throws Exception {
        //given
        Map<String, Object> map = new HashMap<String, Object>();
        int ageExpected = 14;
        map.put("age", ageExpected);
        Map<String, Class<?>> properties = new HashMap<String, Class<?>>();
        properties.put("age", Integer.class);
        //when
        Class testClass = MapProxyTool.classForName("TestClass1", properties, new ClassLoaderServiceImpl());
        Object testClassInstance = testClass.getConstructor(Map.class).newInstance(map);

        //then
        Getter getter = ReflectionTools.getGetter( testClass, "age", "property", serviceRegistry );
        int age = (Integer) getter.get(testClassInstance);
        Assert.assertEquals(ageExpected, age);
    }
