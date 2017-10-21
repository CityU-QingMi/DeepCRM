    public void testSetBeanPropsWrongNonPrimativeType() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("mapValue", new Float(7));
        try {
            factory.setBeanProps(new TestBean(), jobDataMap);
            fail();
        } catch (SchedulerException ignore) {
            // ignore
        }
    }
