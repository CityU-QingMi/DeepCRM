	@Test
	public void indexedReadMethodReturnsSubtypeOfIndexedWriteMethodParameter() throws IntrospectionException {
		@SuppressWarnings("unused") class C {
			public Integer getFoos(int index) { return null; }
			public void setFoo(int index, Number foo) { }
		}

		BeanInfo bi = Introspector.getBeanInfo(C.class);
		BeanInfo ebi = new ExtendedBeanInfo(bi);

		assertThat(hasIndexedReadMethodForProperty(bi, "foos"), is(true));
		assertThat(hasIndexedWriteMethodForProperty(bi, "foos"), is(false));

		assertThat(hasIndexedReadMethodForProperty(ebi, "foos"), is(true));
		assertThat(hasIndexedWriteMethodForProperty(ebi, "foos"), is(false));
	}
