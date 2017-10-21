    public void testSetBeanPropsCharStringTooLong() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("charValue", "abba");
        try {
            factory.setBeanProps(new TestBean(), jobDataMap);
            fail();
        } catch (SchedulerException ignore) {
            // ignore
        }
    }
