	@Test
	public void indexedReadMethodReturnsSupertypeOfIndexedWriteMethodParameter() throws IntrospectionException {
		@SuppressWarnings("unused") class C {
			public Number getFoos(int index) { return null; }
			public void setFoos(int index, Integer foo) { }
		}

		BeanInfo bi = Introspector.getBeanInfo(C.class);
		BeanInfo ebi = new ExtendedBeanInfo(bi);

		assertThat(hasIndexedReadMethodForProperty(bi, "foos"), is(true));
		assertThat(hasIndexedReadMethodForProperty(ebi, "foos"), is(true));
		assertEquals(hasIndexedWriteMethodForProperty(bi, "foos"), hasIndexedWriteMethodForProperty(ebi, "foos"));
	}
