	@Test
	public void testUpCastNotSuper() throws Exception {
		Property property = new Property(getClass(), getClass().getMethod("getProperty"),
				getClass().getMethod("setProperty", Map.class));
		TypeDescriptor typeDescriptor = new TypeDescriptor(property);
		try {
			typeDescriptor.upcast(Collection.class);
			fail("Did not throw");
		}
		catch (IllegalArgumentException ex) {
			assertEquals("interface java.util.Map is not assignable to interface java.util.Collection", ex.getMessage());
		}
	}
