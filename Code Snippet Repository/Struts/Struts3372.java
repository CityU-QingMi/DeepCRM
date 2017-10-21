    public void testSerializeDeserialize() throws Exception {
        Bean bean1 = new Bean();

        bean1.setStringField("str");
        bean1.setBooleanField(true);
        bean1.setCharField('s');
        bean1.setDoubleField(10.1);
        bean1.setFloatField(1.5f);
        bean1.setIntField(10);
        bean1.setLongField(100);
        bean1.setEnumField(AnEnum.ValueA);
        bean1.setEnumBean(AnEnumBean.Two);

        String json = JSONUtil.serialize(bean1, JSONUtil.CACHE_BEAN_INFO_DEFAULT);

        Map result = (Map) JSONUtil.deserialize(json);
        assertEquals("str", result.get("stringField"));
        assertEquals(true, result.get("booleanField"));
        assertEquals("s", result.get("charField")); // note: this is a
                                                            // String
        assertEquals(10.1, result.get("doubleField"));
        assertEquals(1.5, result.get("floatField")); // note: this is a
                                                            // Double
        assertEquals(10L, result.get("intField")); // note: this is a
                                                            // Long
        assertEquals(AnEnum.ValueA, AnEnum.valueOf((String) result.get("enumField"))); // note:
                                                                                        // this
                                                                                        // is a
                                                                                        // String
        assertEquals(AnEnumBean.Two, AnEnumBean.valueOf((String) result.get("enumBean"))); // note:
                                                                                            // this
                                                                                            // is a
                                                                                            // String
    }
