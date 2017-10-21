    public void testTriggerBuilder() throws Exception {
        
        Trigger trigger = newTrigger()
            .build();
        
        assertTrue("Expected non-null trigger name ", trigger.getKey().getName() != null);
        assertTrue("Unexpected trigger group: " + trigger.getKey().getGroup(), trigger.getKey().getGroup().equals(JobKey.DEFAULT_GROUP));
        assertTrue("Unexpected job key: " + trigger.getJobKey(), trigger.getJobKey() == null);
        assertTrue("Unexpected job description: " + trigger.getDescription(), trigger.getDescription() == null);
        assertTrue("Unexpected trigger priortiy: " + trigger.getPriority(), trigger.getPriority() == Trigger.DEFAULT_PRIORITY);
        assertTrue("Unexpected start-time: " + trigger.getStartTime(), trigger.getStartTime() != null);
        assertTrue("Unexpected end-time: " + trigger.getEndTime(), trigger.getEndTime() == null);
        
        Date stime = evenSecondDateAfterNow();
        
        trigger = newTrigger()
            .withIdentity("t1")
            .withDescription("my description")
            .withPriority(2)
            .endAt(futureDate(10, IntervalUnit.WEEK))
            .startAt(stime)
            .build();
        
        assertTrue("Unexpected trigger name " + trigger.getKey().getName(), trigger.getKey().getName().equals("t1"));
        assertTrue("Unexpected trigger group: " + trigger.getKey().getGroup(), trigger.getKey().getGroup().equals(JobKey.DEFAULT_GROUP));
        assertTrue("Unexpected job key: " + trigger.getJobKey(), trigger.getJobKey() == null);
        assertTrue("Unexpected job description: " + trigger.getDescription(), trigger.getDescription().equals("my description"));
        assertTrue("Unexpected trigger priortiy: " + trigger, trigger.getPriority() == 2);
        assertTrue("Unexpected start-time: " + trigger.getStartTime(), trigger.getStartTime().equals(stime));
        assertTrue("Unexpected end-time: " + trigger.getEndTime(), trigger.getEndTime() != null);
        
    }
