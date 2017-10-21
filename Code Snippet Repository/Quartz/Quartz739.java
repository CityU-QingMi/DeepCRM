    public void testClone() {
        SimpleTriggerImpl simpleTrigger = new SimpleTriggerImpl();
        
        // Make sure empty sub-objects are cloned okay
        Trigger clone = (Trigger)simpleTrigger.clone();
        assertEquals(0, clone.getJobDataMap().size());
        
        // Make sure non-empty sub-objects are cloned okay
        simpleTrigger.getJobDataMap().put("K1", "V1");
        simpleTrigger.getJobDataMap().put("K2", "V2");
        clone = (Trigger)simpleTrigger.clone();
        assertEquals(2, clone.getJobDataMap().size());
        assertEquals("V1", clone.getJobDataMap().get("K1"));
        assertEquals("V2", clone.getJobDataMap().get("K2"));
        
        // Make sure sub-object collections have really been cloned by ensuring 
        // their modification does not change the source Trigger 
        clone.getJobDataMap().remove("K1");
        assertEquals(1, clone.getJobDataMap().size());
        
        assertEquals(2, simpleTrigger.getJobDataMap().size());
        assertEquals("V1", simpleTrigger.getJobDataMap().get("K1"));
        assertEquals("V2", simpleTrigger.getJobDataMap().get("K2"));
    }
