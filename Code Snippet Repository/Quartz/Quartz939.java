  @Test
  public void testFired() throws IOException, ClassNotFoundException, ParseException {
    CronTriggerImpl cti = new CronTriggerImpl("triggerName", "triggerGroup", "jobName", "jobGroup", new Date(0L), new Date(10000L), "0 0 12 * * ?", new SimplisticTimeZone("Terra Australis"));
    cti.setDescription("A Trigger");
    cti.setJobDataMap(new JobDataMap());
    cti.setCalendarName("calendarName");
    cti.setMisfireInstruction(MISFIRE_INSTRUCTION_SMART_POLICY);
    cti.setPriority(5);

    cti.triggered(null);
    
    validateSerializedForm(cti, COMPARATOR, expand("serializedforms/CronTriggerImplSerializationTest.testFired.{?}.ser", "JDK16", "JDK17", "JDK18"));
    
  }
