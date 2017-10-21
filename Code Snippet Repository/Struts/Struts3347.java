    public void testObjectBean() throws Exception {
        String text = TestUtils.readContent(JSONInterceptorTest.class.getResource("json-7.txt"));
        Object json = JSONUtil.deserialize(text);
        assertNotNull(json);
        assertTrue(json instanceof Map);
        Map jsonMap = (Map) json;
        JSONPopulator populator = new JSONPopulator();
        WrapperClassBean bean = new WrapperClassBean();
        populator.populateObject(bean, jsonMap);
        assertEquals(Boolean.TRUE, bean.getBooleanField());
        assertEquals(true, bean.isPrimitiveBooleanField1());
        assertEquals(false, bean.isPrimitiveBooleanField2());
        assertEquals(false, bean.isPrimitiveBooleanField3());
        assertEquals("test\u000E\u000f", bean.getStringField());
        assertEquals(new Integer(10), bean.getIntField());
        assertEquals(0, bean.getNullIntField());
        assertEquals(new Character('s'), bean.getCharField());
        assertEquals(10.1d, bean.getDoubleField());
        assertEquals(new Byte((byte) 3), bean.getByteField());

        assertEquals(2, bean.getListField().size());
        assertEquals("1", bean.getListField().get(0).getValue());
        assertEquals("2", bean.getListField().get(1).getValue());

        assertEquals(1, bean.getListMapField().size());
        assertEquals(2, bean.getListMapField().get(0).size());
        assertEquals(new Long(2073501), bean.getListMapField().get(0).get("id1"));
        assertEquals(new Long(3), bean.getListMapField().get(0).get("id2"));

        assertEquals(2, bean.getMapListField().size());
        assertEquals(3, bean.getMapListField().get("id1").size());
        assertEquals(new Long(2), bean.getMapListField().get("id1").get(1));
        assertEquals(4, bean.getMapListField().get("id2").size());
        assertEquals(new Long(3), bean.getMapListField().get("id2").get(1));

        assertEquals(1, bean.getArrayMapField().length);
        assertEquals(2, bean.getArrayMapField()[0].size());
        assertEquals(new Long(2073501), bean.getArrayMapField()[0].get("id1"));
        assertEquals(new Long(3), bean.getArrayMapField()[0].get("id2"));
    }
