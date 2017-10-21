    @SuppressWarnings("")
    public void testAcquireNextTrigger() throws Exception {
    	
    	Date baseFireTimeDate = DateBuilder.evenMinuteDateAfterNow();
    	long baseFireTime = baseFireTimeDate.getTime();
    	
        OperableTrigger trigger1 = 
            new SimpleTriggerImpl("trigger1", "triggerGroup1", this.fJobDetail.getName(), 
                    this.fJobDetail.getGroup(), new Date(baseFireTime + 200000), 
                    new Date(baseFireTime + 200000), 2, 2000);
        OperableTrigger trigger2 = 
            new SimpleTriggerImpl("trigger2", "triggerGroup1", this.fJobDetail.getName(), 
                    this.fJobDetail.getGroup(), new Date(baseFireTime +  50000),
                    new Date(baseFireTime + 200000), 2, 2000);
        OperableTrigger trigger3 = 
            new SimpleTriggerImpl("trigger1", "triggerGroup2", this.fJobDetail.getName(), 
                    this.fJobDetail.getGroup(), new Date(baseFireTime + 100000), 
                    new Date(baseFireTime + 200000), 2, 2000);

        trigger1.computeFirstFireTime(null);
        trigger2.computeFirstFireTime(null);
        trigger3.computeFirstFireTime(null);
        this.fJobStore.storeTrigger(trigger1, false);
        this.fJobStore.storeTrigger(trigger2, false);
        this.fJobStore.storeTrigger(trigger3, false);
        
        long firstFireTime = new Date(trigger1.getNextFireTime().getTime()).getTime();

        assertTrue(this.fJobStore.acquireNextTriggers(10, 1, 0L).isEmpty());
        assertEquals(
            trigger2.getKey(), 
            this.fJobStore.acquireNextTriggers(firstFireTime + 10000, 1, 0L).get(0).getKey());
        assertEquals(
            trigger3.getKey(), 
            this.fJobStore.acquireNextTriggers(firstFireTime + 10000, 1, 0L).get(0).getKey());
        assertEquals(
            trigger1.getKey(), 
            this.fJobStore.acquireNextTriggers(firstFireTime + 10000, 1, 0L).get(0).getKey());
        assertTrue(
            this.fJobStore.acquireNextTriggers(firstFireTime + 10000, 1, 0L).isEmpty());


        // release trigger3
        this.fJobStore.releaseAcquiredTrigger(trigger3);
        assertEquals(
            trigger3, 
            this.fJobStore.acquireNextTriggers(new Date(trigger1.getNextFireTime().getTime()).getTime() + 10000, 1, 1L).get(0));
    }
