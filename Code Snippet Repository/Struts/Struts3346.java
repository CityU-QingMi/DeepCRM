    public void testPrimitiveBean() throws Exception {
        StringReader stringReader = new StringReader(TestUtils.readContent(JSONInterceptorTest.class
                .getResource("json-7.txt")));
        Object json = JSONUtil.deserialize(stringReader);
        assertNotNull(json);
        assertTrue(json instanceof Map);
        Map jsonMap = (Map) json;
        JSONPopulator populator = new JSONPopulator();
        Bean bean = new Bean();
        populator.populateObject(bean, jsonMap);
        assertTrue(bean.isBooleanField());
        assertEquals("test\u000E\u000f", bean.getStringField());
        assertEquals(10, bean.getIntField());
        assertEquals('s', bean.getCharField());
        assertEquals(10.1d, bean.getDoubleField(), 0d);
        assertEquals(3, bean.getByteField());
        assertEquals(new BigDecimal(111111.5d), bean.getBigDecimal());
        assertEquals(new BigInteger("111111"), bean.getBigInteger());
    }
