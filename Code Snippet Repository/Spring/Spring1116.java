	@Test
	public void nonStandardWriteMethodOnly() throws IntrospectionException {
		@SuppressWarnings("unused") class C {
			public C setFoo(String foo) { return this; }
		}

		BeanInfo bi = Introspector.getBeanInfo(C.class);
		ExtendedBeanInfo ebi = new ExtendedBeanInfo(bi);

		assertThat(hasReadMethodForProperty(bi, "foo"), is(false));
		assertThat(hasWriteMethodForProperty(bi, "foo"), is(false));

		assertThat(hasReadMethodForProperty(ebi, "foo"), is(false));
		assertThat(hasWriteMethodForProperty(ebi, "foo"), is(true));
	}
