	@Test
	public void getPropertyWithOptionalAndAutoGrow() {
		GetterWithOptional target = new GetterWithOptional();
		BeanWrapper accessor = createAccessor(target);
		accessor.setAutoGrowNestedPaths(true);

		accessor.setPropertyValue("object.name", "x");
		assertEquals("x", target.value.getName());
		assertEquals("x", target.getObject().get().getName());
		assertEquals("x", accessor.getPropertyValue("object.name"));
	}
