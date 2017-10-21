	@Test
	public void setUnknownProperty() {
		Simple target = new Simple("John", 2);
		AbstractPropertyAccessor accessor = createAccessor(target);

		try {
			accessor.setPropertyValue("name1", "value");
			fail("Should have failed to set an unknown property.");
		}
		catch (NotWritablePropertyException e) {
			assertEquals(Simple.class, e.getBeanClass());
			assertEquals("name1", e.getPropertyName());
			assertEquals("Invalid number of possible matches", 1, e.getPossibleMatches().length);
			assertEquals("name", e.getPossibleMatches()[0]);
		}
	}
