	@Test
	public void setEnumProperty() {
		EnumTester target = new EnumTester();
		AbstractPropertyAccessor accessor = createAccessor(target);

		accessor.setPropertyValue("autowire", "BY_NAME");
		assertEquals(Autowire.BY_NAME, target.getAutowire());

		accessor.setPropertyValue("autowire", "  BY_TYPE ");
		assertEquals(Autowire.BY_TYPE, target.getAutowire());

		try {
			accessor.setPropertyValue("autowire", "NHERITED");
			fail("Should have thrown TypeMismatchException");
		}
		catch (TypeMismatchException ex) {
			// expected
		}
	}
