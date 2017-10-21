	@Test
	public void getPropertyIntermediatePropertyIsNull() {
		Person target = createPerson("John", "London", "UK");
		target.address = null;
		AbstractPropertyAccessor accessor = createAccessor(target);

		try {
			accessor.getPropertyValue("address.country.name");
			fail("Should have failed to get value with null intermediate path");
		}
		catch (NullValueInNestedPathException e) {
			assertEquals("address", e.getPropertyName());
			assertEquals(Person.class, e.getBeanClass());
		}
	}
