    public void testClone() throws ParseException {
        CronTriggerImpl trigger = new CronTriggerImpl();
        trigger.setName("test");
        trigger.setGroup("testGroup");
        trigger.setCronExpression("0 0 12 * * ?");
        CronTrigger trigger2 = (CronTrigger) trigger.clone();

        assertEquals( "Cloning failed", trigger, trigger2 );

        // equals() doesn't test the cron expression
        assertEquals( "Cloning failed for the cron expression", 
                      "0 0 12 * * ?", trigger2.getCronExpression()
                    );
    }
