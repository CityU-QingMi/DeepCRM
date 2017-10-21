    public void testSetBeanPropsPrimatives() throws SchedulerException {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("intValue", Integer.valueOf(1));
        jobDataMap.put("longValue", Long.valueOf(2l));
        jobDataMap.put("floatValue", Float.valueOf(3.0f));
        jobDataMap.put("doubleValue", Double.valueOf(4.0));
        jobDataMap.put("booleanValue", Boolean.TRUE);
        jobDataMap.put("shortValue", Short.valueOf(((short)5)));
        jobDataMap.put("charValue", 'a');
        jobDataMap.put("byteValue", Byte.valueOf((byte)6));
        jobDataMap.put("stringValue", "S1");
        jobDataMap.put("mapValue", Collections.singletonMap("A", "B"));
        
        TestBean myBean = new TestBean();
        factory.setBeanProps(myBean, jobDataMap);
        
        assertEquals(1, myBean.getIntValue());
        assertEquals(2l, myBean.getLongValue());
        assertEquals(3.0f, myBean.getFloatValue(), 0.0001);
        assertEquals(4.0, myBean.getDoubleValue(), 0.0001);
        assertTrue(myBean.getBooleanValue());
        assertEquals(5, myBean.getShortValue());
        assertEquals('a', myBean.getCharValue());
        assertEquals((byte)6, myBean.getByteValue());
        assertEquals("S1", myBean.getStringValue());
        assertTrue(myBean.getMapValue().containsKey("A"));
    }
