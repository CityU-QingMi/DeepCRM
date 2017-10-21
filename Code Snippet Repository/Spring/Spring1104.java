	@Test
	public void overloadedNonStandardWriteMethodsOnly_orderA() throws IntrospectionException, SecurityException, NoSuchMethodException {
		@SuppressWarnings("unused") class C {
			public Object setFoo(String p) { return new Object(); }
			public Object setFoo(int p) { return new Object(); }
		}
		BeanInfo bi = Introspector.getBeanInfo(C.class);

		assertThat(hasReadMethodForProperty(bi, "foo"), is(false));
		assertThat(hasWriteMethodForProperty(bi, "foo"), is(false));

		BeanInfo ebi = new ExtendedBeanInfo(bi);

		assertThat(hasReadMethodForProperty(bi, "foo"), is(false));
		assertThat(hasWriteMethodForProperty(bi, "foo"), is(false));

		assertThat(hasReadMethodForProperty(ebi, "foo"), is(false));
		assertThat(hasWriteMethodForProperty(ebi, "foo"), is(true));

		for (PropertyDescriptor pd : ebi.getPropertyDescriptors()) {
			if (pd.getName().equals("foo")) {
				assertThat(pd.getWriteMethod(), is(C.class.getMethod("setFoo", String.class)));
				return;
			}
		}
		fail("never matched write method");
	}
