	@Test
	public void standardReadAndNonStandardWriteMethods() throws IntrospectionException {
		@SuppressWarnings("unused") class C {
			public String getFoo() { return null; }
			public C setFoo(String foo) { return this; }
		}

		BeanInfo bi = Introspector.getBeanInfo(C.class);

		assertThat(hasReadMethodForProperty(bi, "foo"), is(true));
		assertThat(hasWriteMethodForProperty(bi, "foo"), is(false));

		ExtendedBeanInfo ebi = new ExtendedBeanInfo(bi);

		assertThat(hasReadMethodForProperty(bi, "foo"), is(true));
		assertThat(hasWriteMethodForProperty(bi, "foo"), is(false));

		assertThat(hasReadMethodForProperty(ebi, "foo"), is(true));
		assertThat(hasWriteMethodForProperty(ebi, "foo"), is(true));
	}
