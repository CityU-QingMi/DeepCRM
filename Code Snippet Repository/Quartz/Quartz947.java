  @Test
  public void testFired() throws IOException, ClassNotFoundException {
    SimpleTriggerImpl sti = new SimpleTriggerImpl("triggerName", "triggerGroup", "jobName", "jobGroup", new Date(0L), new Date(10000L), 4, 100L);
    sti.setDescription("A Trigger");
    sti.setJobDataMap(new JobDataMap());
    sti.setCalendarName("calendarName");
    sti.setMisfireInstruction(MISFIRE_INSTRUCTION_SMART_POLICY);
    sti.setPriority(5);

    sti.triggered(null);
    
    validateSerializedForm(sti, COMPARATOR, expand("serializedforms/SimpleTriggerImplSerializationTest.testFired.{?}.ser", "JDK16", "JDK17", "JDK18"));
    
  }
