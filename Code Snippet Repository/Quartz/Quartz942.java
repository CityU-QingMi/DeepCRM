  @Test
  public void testFired() throws IOException, ClassNotFoundException {
    DailyTimeIntervalTriggerImpl dti = new DailyTimeIntervalTriggerImpl("triggerName", "triggerGroup", "jobName", "jobGroup", new Date(0L), new Date(10000L), new TimeOfDay(10, 45, 30), new TimeOfDay(23, 30, 15), MINUTE,  5);
    dti.setDescription("A Trigger");
    dti.setJobDataMap(new JobDataMap());
    dti.setCalendarName("calendarName");
    dti.setMisfireInstruction(MISFIRE_INSTRUCTION_SMART_POLICY);
    dti.setPriority(5);

    dti.triggered(null);
    
    validateSerializedForm(dti, COMPARATOR, expand("serializedforms/DailyTimeIntervalTriggerImplSerializationTest.testFired.{?}.ser", "JDK16", "JDK17_1", "JDK17_2", "JDK18"));
  }
