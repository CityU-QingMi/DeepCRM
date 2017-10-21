    public void testObjectBeanWithStrings() throws Exception {
        StringReader stringReader = new StringReader(TestUtils.readContent(JSONInterceptorTest.class
                .getResource("json-8.txt")));
        Object json = JSONUtil.deserialize(stringReader);
        assertNotNull(json);
        assertTrue(json instanceof Map);
        Map jsonMap = (Map) json;
        JSONPopulator populator = new JSONPopulator();
        WrapperClassBean bean = new WrapperClassBean();
        populator.populateObject(bean, jsonMap);
        assertEquals(Boolean.TRUE, bean.getBooleanField());
        assertEquals("test", bean.getStringField());
        assertEquals(new Integer(10), bean.getIntField());
        assertEquals(new Character('s'), bean.getCharField());
        assertEquals(10.1d, bean.getDoubleField());
        assertEquals(new Byte((byte) 3), bean.getByteField());

        assertEquals(null, bean.getListField());
        assertEquals(null, bean.getListMapField());
        assertEquals(null, bean.getMapListField());
        assertEquals(null, bean.getArrayMapField());
    }
