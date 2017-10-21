	@Test
	public void standardWriteMethodOnly() throws IntrospectionException {
		@SuppressWarnings("unused") class C {
			public void setFoo(String f) { }
		}

		BeanInfo bi = Introspector.getBeanInfo(C.class);
		ExtendedBeanInfo ebi = new ExtendedBeanInfo(bi);

		assertThat(hasReadMethodForProperty(bi, "foo"), is(false));
		assertThat(hasWriteMethodForProperty(bi, "foo"), is(true));

		assertThat(hasReadMethodForProperty(ebi, "foo"), is(false));
		assertThat(hasWriteMethodForProperty(ebi, "foo"), is(true));
	}
