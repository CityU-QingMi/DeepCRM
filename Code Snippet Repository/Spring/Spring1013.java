	@Test
	public void setUnknownPropertyWithPossibleMatches() {
		Simple target = new Simple("John", 2);
		AbstractPropertyAccessor accessor = createAccessor(target);

		try {
			accessor.setPropertyValue("foo", "value");
			fail("Should have failed to set an unknown property.");
		}
		catch (NotWritablePropertyException e) {
			assertEquals(Simple.class, e.getBeanClass());
			assertEquals("foo", e.getPropertyName());
		}
	}
