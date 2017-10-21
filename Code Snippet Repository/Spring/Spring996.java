	@Test
	public void setArrayPropertyToObject() {
		ArrayToObject target = new ArrayToObject();
		AbstractPropertyAccessor accessor = createAccessor(target);

		Object[] array = new Object[] {"1", "2"};
		accessor.setPropertyValue("object", array);
		assertThat(target.getObject(), equalTo((Object) array));

		array = new Object[] {"1"};
		accessor.setPropertyValue("object", array);
		assertThat(target.getObject(), equalTo((Object) array));
	}
