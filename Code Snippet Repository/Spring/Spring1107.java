	@Test
	public void propertyCountsWithNonStandardWriteMethod() throws IntrospectionException {
		class ExtendedTestBean extends TestBean {
			@SuppressWarnings("unused")
			public ExtendedTestBean setFoo(String s) { return this; }
		}
		BeanInfo bi = Introspector.getBeanInfo(ExtendedTestBean.class);
		BeanInfo ebi = new ExtendedBeanInfo(bi);

		boolean found = false;
		for (PropertyDescriptor pd : ebi.getPropertyDescriptors()) {
			if (pd.getName().equals("foo")) {
				found = true;
			}
		}
		assertThat(found, is(true));
		assertThat(ebi.getPropertyDescriptors().length, equalTo(bi.getPropertyDescriptors().length+1));
	}
