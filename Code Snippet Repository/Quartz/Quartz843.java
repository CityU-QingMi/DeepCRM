    public void testSetBeanPropsFromStrings() throws SchedulerException {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("intValue", "1");
        jobDataMap.put("longValue", "2");
        jobDataMap.put("floatValue", "3.0");
        jobDataMap.put("doubleValue", "4.0");
        jobDataMap.put("booleanValue", "true");
        jobDataMap.put("shortValue", "5");
        jobDataMap.put("charValue", "a");
        jobDataMap.put("byteValue", "6");
        
        TestBean myBean = new TestBean();
        factory.setBeanProps(myBean, jobDataMap);
        
        assertEquals(1, myBean.getIntValue());
        assertEquals(2l, myBean.getLongValue());
        assertEquals(3.0f, myBean.getFloatValue(), 0.0001);
        assertEquals(4.0, myBean.getDoubleValue(), 0.0001);
        assertEquals(true, myBean.getBooleanValue());
        assertEquals(5, myBean.getShortValue());
        assertEquals('a', myBean.getCharValue());
        assertEquals((byte)6, myBean.getByteValue());
    }
