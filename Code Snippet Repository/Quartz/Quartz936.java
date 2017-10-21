  @Test
  public void testFired() throws IOException, ClassNotFoundException {
    CalendarIntervalTriggerImpl cti = new CalendarIntervalTriggerImpl("triggerName", "triggerGroup", "jobName", "jobGroup", new Date(0L), new Date(10000L), MINUTE,  5);
    cti.setTimeZone(new SimplisticTimeZone("Terra Australis"));
    cti.setPreserveHourOfDayAcrossDaylightSavings(true);
    cti.setSkipDayIfHourDoesNotExist(true);
    cti.setDescription("A Trigger");
    cti.setJobDataMap(new JobDataMap());
    cti.setCalendarName("calendarName");
    cti.setMisfireInstruction(MISFIRE_INSTRUCTION_SMART_POLICY);
    cti.setPriority(5);

    cti.triggered(null);
    
    validateSerializedForm(cti, COMPARATOR, expand("serializedforms/CalendarIntervalTriggerImplSerializationTest.testFired.{?}.ser", "JDK16", "JDK17", "JDK18"));
    
  }
