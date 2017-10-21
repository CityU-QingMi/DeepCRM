	@Test
	public void setPropertyIntermediatePropertyIsNull() {
		Person target = createPerson("John", "Paris", "FR");
		target.address.country = null;
		AbstractPropertyAccessor accessor = createAccessor(target);

		try {
			accessor.setPropertyValue("address.country.name", "UK");
			fail("Should have failed to set value with intermediate null value");
		}
		catch (NullValueInNestedPathException e) {
			assertEquals("address.country", e.getPropertyName());
			assertEquals(Person.class, e.getBeanClass());
		}
		assertThat(target.address.country, is(nullValue())); // Not touched
	}
