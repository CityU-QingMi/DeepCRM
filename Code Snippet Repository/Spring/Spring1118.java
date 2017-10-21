	@Test
	public void standardReadAndNonStandardIndexedWriteMethod() throws IntrospectionException {
		@SuppressWarnings("unused") class C {
			public String[] getFoo() { return null; }
			public C setFoo(int i, String foo) { return this; }
		}

		BeanInfo bi = Introspector.getBeanInfo(C.class);

		assertThat(hasReadMethodForProperty(bi, "foo"), is(true));
		assertThat(hasWriteMethodForProperty(bi, "foo"), is(false));
		assertThat(hasIndexedWriteMethodForProperty(bi, "foo"), is(false));

		BeanInfo ebi = new ExtendedBeanInfo(bi);

		assertThat(hasReadMethodForProperty(ebi, "foo"), is(true));
		assertThat(hasWriteMethodForProperty(ebi, "foo"), is(false));
		assertThat(hasIndexedWriteMethodForProperty(ebi, "foo"), is(true));
	}
