    public void testResetErrorTrigger() throws Exception {

        Date baseFireTimeDate = DateBuilder.evenMinuteDateAfterNow();
        long baseFireTime = baseFireTimeDate.getTime();

        // create and store a trigger
        OperableTrigger trigger1 =
                new SimpleTriggerImpl("trigger1", "triggerGroup1", this.fJobDetail.getName(),
                        this.fJobDetail.getGroup(), new Date(baseFireTime + 200000),
                        new Date(baseFireTime + 200000), 2, 2000);

        trigger1.computeFirstFireTime(null);
        this.fJobStore.storeTrigger(trigger1, false);

        long firstFireTime = new Date(trigger1.getNextFireTime().getTime()).getTime();


        // pretend to fire it
        List<OperableTrigger> aqTs = this.fJobStore.acquireNextTriggers(
                firstFireTime + 10000, 1, 0L);
        assertEquals(trigger1.getKey(), aqTs.get(0).getKey());

        List<TriggerFiredResult> fTs = this.fJobStore.triggersFired(aqTs);
        TriggerFiredResult ft = fTs.get(0);

        // get the trigger into error state
        this.fJobStore.triggeredJobComplete(ft.getTriggerFiredBundle().getTrigger(), ft.getTriggerFiredBundle().getJobDetail(), Trigger.CompletedExecutionInstruction.SET_TRIGGER_ERROR);
        TriggerState state = this.fJobStore.getTriggerState(trigger1.getKey());
        assertEquals(TriggerState.ERROR, state);

        // test reset
        this.fJobStore.resetTriggerFromErrorState(trigger1.getKey());
        state = this.fJobStore.getTriggerState(trigger1.getKey());
        assertEquals(TriggerState.NORMAL, state);
    }
