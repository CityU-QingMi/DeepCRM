	@Test
	public void constants() {
		Constants c = new Constants(A.class);
		assertEquals(A.class.getName(), c.getClassName());
		assertEquals(9, c.getSize());

		assertEquals(c.asNumber("DOG").intValue(), A.DOG);
		assertEquals(c.asNumber("dog").intValue(), A.DOG);
		assertEquals(c.asNumber("cat").intValue(), A.CAT);

		try {
			c.asNumber("bogus");
			fail("Can't get bogus field");
		}
		catch (Constants.ConstantException expected) {
		}

		assertTrue(c.asString("S1").equals(A.S1));
		try {
			c.asNumber("S1");
			fail("Wrong type");
		}
		catch (Constants.ConstantException expected) {
		}
	}
