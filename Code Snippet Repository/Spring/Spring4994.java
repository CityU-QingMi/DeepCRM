	@Test
	public void getValuesInTurkey() {
		Locale oldLocale = Locale.getDefault();
		Locale.setDefault(new Locale("tr", ""));
		try {
			Constants c = new Constants(A.class);

			Set<?> values = c.getValues("");
			assertEquals(7, values.size());
			assertTrue(values.contains(new Integer(0)));
			assertTrue(values.contains(new Integer(66)));
			assertTrue(values.contains(""));

			values = c.getValues("D");
			assertEquals(1, values.size());
			assertTrue(values.contains(new Integer(0)));

			values = c.getValues("prefix");
			assertEquals(2, values.size());
			assertTrue(values.contains(new Integer(1)));
			assertTrue(values.contains(new Integer(2)));

			values = c.getValuesForProperty("myProperty");
			assertEquals(2, values.size());
			assertTrue(values.contains(new Integer(1)));
			assertTrue(values.contains(new Integer(2)));
		}
		finally {
			Locale.setDefault(oldLocale);
		}
	}
