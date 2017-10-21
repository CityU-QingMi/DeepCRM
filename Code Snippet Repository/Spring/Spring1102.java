	@Test
	public void nonStandardReadMethodAndStandardWriteMethod() throws IntrospectionException {
		@SuppressWarnings("unused") class C {
			public void getFoo() { }
			public void setFoo(String foo) { }
		}

		BeanInfo bi = Introspector.getBeanInfo(C.class);
		BeanInfo ebi = new ExtendedBeanInfo(bi);

		assertThat(hasReadMethodForProperty(bi, "foo"), is(false));
		assertThat(hasWriteMethodForProperty(bi, "foo"), is(true));

		assertThat(hasReadMethodForProperty(ebi, "foo"), is(false));
		assertThat(hasWriteMethodForProperty(ebi, "foo"), is(true));
	}
