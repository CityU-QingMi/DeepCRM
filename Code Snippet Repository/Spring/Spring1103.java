	@Test
	public void emptyPropertiesIgnored() throws IntrospectionException {
		@SuppressWarnings("unused") class C {
			public Object set(Object o) { return null; }
			public Object set(int i, Object o) { return null; }
		}

		BeanInfo bi = Introspector.getBeanInfo(C.class);
		BeanInfo ebi = new ExtendedBeanInfo(bi);

		assertThat(ebi.getPropertyDescriptors(), equalTo(bi.getPropertyDescriptors()));
	}
