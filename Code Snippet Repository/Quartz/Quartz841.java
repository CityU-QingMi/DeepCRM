    public void testSetBeanPropsCharStringTooShort() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("charValue", "");
        try {
            factory.setBeanProps(new TestBean(), jobDataMap);
            fail();
        } catch (SchedulerException ignore) {
            // ignroe
        }
    }
