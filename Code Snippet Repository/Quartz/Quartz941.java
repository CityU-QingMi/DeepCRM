  @Test
  public void testConstructed() throws IOException, ClassNotFoundException {
    DailyTimeIntervalTriggerImpl dti = new DailyTimeIntervalTriggerImpl("triggerName", "triggerGroup", "jobName", "jobGroup", new Date(0L), new Date(10000L), new TimeOfDay(10, 45, 30), new TimeOfDay(23, 30, 15), MINUTE,  5);
    dti.setDescription("A Trigger");
    dti.setJobDataMap(new JobDataMap());
    dti.setCalendarName("calendarName");
    dti.setMisfireInstruction(MISFIRE_INSTRUCTION_SMART_POLICY);
    dti.setPriority(5);
    
    validateSerializedForm(dti, COMPARATOR, expand("serializedforms/DailyTimeIntervalTriggerImplSerializationTest.testConstructed.{?}.ser", "JDK16", "JDK17", "JDK18"));
  }
