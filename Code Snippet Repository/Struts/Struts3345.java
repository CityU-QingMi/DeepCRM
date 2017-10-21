    public void testNulls() throws IntrospectionException, InvocationTargetException, NoSuchMethodException,
            JSONException, InstantiationException, IllegalAccessException {
        JSONPopulator populator = new JSONPopulator();
        OtherBean bean = new OtherBean();
        Map jsonMap = new HashMap();

        jsonMap.put("intField", null);
        jsonMap.put("booleanField", null);
        jsonMap.put("charField", null);
        jsonMap.put("longField", null);
        jsonMap.put("floatField", null);
        jsonMap.put("doubleField", null);
        jsonMap.put("byteField", null);

        populator.populateObject(bean, jsonMap);
        assertNull(bean.getIntField());
        assertNull(bean.isBooleanField());
        assertNull(bean.getCharField());
        assertNull(bean.getLongField());
        assertNull(bean.getDoubleField());
        assertNull(bean.getByteField());
    }
