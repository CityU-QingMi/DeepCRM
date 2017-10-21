    public void testSetBeanPropsNullPrimative() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("intValue", null);
        try {
            factory.setBeanProps(new TestBean(), jobDataMap);
            fail();
        } catch (SchedulerException ignore) {
            // ignore
        }
    }
