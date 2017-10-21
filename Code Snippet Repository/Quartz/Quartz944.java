  @Test
  public void testComplex() throws IOException, ClassNotFoundException {
    JobDetailImpl jdi = new JobDetailImpl();
    jdi.setName("foo");
    jdi.setGroup("bar");
    jdi.setJobClass(Job.class);
    
    jdi.setDescription("My really fancy and complicated job");
    jdi.setJobDataMap(new JobDataMap(Collections.singletonMap("foo", "bar")));
    jdi.setDurability(true);
    jdi.setRequestsRecovery(true);
    
    validateSerializedForm(jdi, COMPARATOR, "serializedforms/JobDetailImplSerializationTest.testComplex.ser");
  }
