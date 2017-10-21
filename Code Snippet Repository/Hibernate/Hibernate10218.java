    @Test
    public void shouldGenerateClassWithAppropriateSetter() throws Exception {
        //given
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Class<?>> properties = new HashMap<String, Class<?>>();
        properties.put("age", Integer.class);

        //when
        Class testClass = MapProxyTool.classForName("TestClass2", properties, new ClassLoaderServiceImpl());
        Object testClassInstance = testClass.getConstructor(Map.class).newInstance(map);

        //then
        Setter setter = ReflectionTools.getSetter(testClass, "age", "property", serviceRegistry);
        int ageExpected = 14;
        setter.set(testClassInstance, ageExpected, null);
        Object age = map.get("age");
        Assert.assertEquals(ageExpected, age);
    }
