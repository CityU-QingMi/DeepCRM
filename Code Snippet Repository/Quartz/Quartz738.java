    public void testGetFireTimeAfter() {
        SimpleTriggerImpl simpleTrigger = new SimpleTriggerImpl();

        simpleTrigger.setStartTime(new Date(0));
        simpleTrigger.setRepeatInterval(10);
        simpleTrigger.setRepeatCount(4);
        
        Date fireTimeAfter = simpleTrigger.getFireTimeAfter(new Date(34));
        assertEquals(40, fireTimeAfter.getTime());
    }
