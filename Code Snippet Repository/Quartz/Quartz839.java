    public void testSetBeanPropsWrongPrimativeType() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("intValue", new Float(7));
        try {
            factory.setBeanProps(new TestBean(), jobDataMap);
            fail();
        } catch (SchedulerException ignore) {
            // ignore
        }
    }
