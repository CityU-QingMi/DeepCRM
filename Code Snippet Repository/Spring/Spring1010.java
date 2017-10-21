	@Test
	public void getUnknownProperty() {
		Simple target = new Simple("John", 2);
		AbstractPropertyAccessor accessor = createAccessor(target);

		try {
			accessor.getPropertyValue("foo");
			fail("Should have failed to get an unknown property.");
		}
		catch (NotReadablePropertyException e) {
			assertEquals(Simple.class, e.getBeanClass());
			assertEquals("foo", e.getPropertyName());
		}
	}
