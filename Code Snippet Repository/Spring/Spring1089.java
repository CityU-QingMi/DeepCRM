	@Test
	public void readMethodReturnsSupertypeOfWriteMethodParameter() throws IntrospectionException {
		@SuppressWarnings("unused") class C {
			public Number getFoo() { return null; }
			public void setFoo(Integer foo) { }
		}

		BeanInfo bi = Introspector.getBeanInfo(C.class);
		BeanInfo ebi = new ExtendedBeanInfo(bi);

		assertThat(hasReadMethodForProperty(bi, "foo"), is(true));
		assertThat(hasReadMethodForProperty(ebi, "foo"), is(true));
		assertEquals(hasWriteMethodForProperty(bi, "foo"), hasWriteMethodForProperty(ebi, "foo"));
	}
