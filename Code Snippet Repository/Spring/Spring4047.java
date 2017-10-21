	@Test
	public void testOptionalProperty() {
		OptionalHolder bean = new OptionalHolder();
		DataBinder binder = new DataBinder(bean);
		binder.setConversionService(new DefaultConversionService());

		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("id", "1");
		pvs.add("name", null);
		binder.bind(pvs);
		assertEquals("1", bean.getId());
		assertFalse(bean.getName().isPresent());

		pvs = new MutablePropertyValues();
		pvs.add("id", "2");
		pvs.add("name", "myName");
		binder.bind(pvs);
		assertEquals("2", bean.getId());
		assertEquals("myName", bean.getName().get());
	}
