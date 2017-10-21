	@Test
	public void getPropertyWithOptional() {
		GetterWithOptional target = new GetterWithOptional();
		TestBean tb = new TestBean("x");
		BeanWrapper accessor = createAccessor(target);

		accessor.setPropertyValue("object", tb);
		assertSame(tb, target.value);
		assertSame(tb, target.getObject().get());
		assertSame(tb, ((Optional<String>) accessor.getPropertyValue("object")).get());
		assertEquals("x", target.value.getName());
		assertEquals("x", target.getObject().get().getName());
		assertEquals("x", accessor.getPropertyValue("object.name"));

		accessor.setPropertyValue("object.name", "y");
		assertSame(tb, target.value);
		assertSame(tb, target.getObject().get());
		assertSame(tb, ((Optional<String>) accessor.getPropertyValue("object")).get());
		assertEquals("y", target.value.getName());
		assertEquals("y", target.getObject().get().getName());
		assertEquals("y", accessor.getPropertyValue("object.name"));
	}
