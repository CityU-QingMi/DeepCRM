    @Test
    @SuppressWarnings("")
    public void testDottedLowerI() {
        final Locale defaultLocale = Locale.getDefault();
        final Locale turkey = new Locale("tr", "TR");
        Locale.setDefault(turkey);
        final Priority level = Priority.toPriority("info");
        Locale.setDefault(defaultLocale);
        assertEquals("INFO", level.toString());
  }
