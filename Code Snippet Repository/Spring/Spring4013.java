	@Test
	public void nestedBindingWithDisabledAutoGrow() throws Exception {
		FieldAccessBean rod = new FieldAccessBean();
		DataBinder binder = new DataBinder(rod, "person");
		binder.setAutoGrowNestedPaths(false);
		binder.initDirectFieldAccess();
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.addPropertyValue(new PropertyValue("spouse.name", "Kerry"));

		thrown.expect(NullValueInNestedPathException.class);
		binder.bind(pvs);
	}
