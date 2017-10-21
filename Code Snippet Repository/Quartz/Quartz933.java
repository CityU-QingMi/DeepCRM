  @Test
  public void testBaseCalendarAndComplex() throws IOException, ClassNotFoundException {
    AnnualCalendar ac = new AnnualCalendar(new HolidayCalendar());
    Calendar exclude = Calendar.getInstance(new SimplisticTimeZone("Atlantis"), Locale.ROOT);
    exclude.clear();
    exclude.set(MONTH, DECEMBER);
    exclude.set(DAY_OF_MONTH, 29);
    ac.setDayExcluded(exclude, true);
    ac.setTimeZone(new SimplisticTimeZone("Terra Australis"));
    ac.setDescription("Annual Calendar");
    validateSerializedForm(ac, COMPARATOR, expand("serializedforms/AnnualCalendarSerializationTest.testBaseCalendarAndComplex.{?}.ser", "JDK16", "JDK17"));
  }  
