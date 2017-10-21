	@Test
	public void propertyDescriptors() {
		TestBean target = new TestBean();
		target.setSpouse(new TestBean());
		BeanWrapper accessor = createAccessor(target);
		accessor.setPropertyValue("name", "a");
		accessor.setPropertyValue("spouse.name", "b");
		assertEquals("a", target.getName());
		assertEquals("b", target.getSpouse().getName());
		assertEquals("a", accessor.getPropertyValue("name"));
		assertEquals("b", accessor.getPropertyValue("spouse.name"));
		assertEquals(String.class, accessor.getPropertyDescriptor("name").getPropertyType());
		assertEquals(String.class, accessor.getPropertyDescriptor("spouse.name").getPropertyType());
	}
