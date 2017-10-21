	@Test
	public void readMethodReturnsSubtypeOfWriteMethodParameter() throws IntrospectionException {
		@SuppressWarnings("unused") class C {
			public Integer getFoo() { return null; }
			public void setFoo(Number foo) { }
		}

		BeanInfo bi = Introspector.getBeanInfo(C.class);
		BeanInfo ebi = new ExtendedBeanInfo(bi);

		assertThat(hasReadMethodForProperty(bi, "foo"), is(true));
		assertThat(hasWriteMethodForProperty(bi, "foo"), is(false));

		assertThat(hasReadMethodForProperty(ebi, "foo"), is(true));
		assertThat(hasWriteMethodForProperty(ebi, "foo"), is(false));
	}
