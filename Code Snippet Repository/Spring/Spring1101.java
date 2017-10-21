	@Test
	public void subclassWriteMethodWithCovariantReturnType() throws IntrospectionException {
		@SuppressWarnings("unused") class B {
			public String getFoo() { return null; }
			public Number setFoo(String foo) { return null; }
		}
		class C extends B {
			@Override
			public String getFoo() { return null; }
			@Override
			public Integer setFoo(String foo) { return null; }
		}

		BeanInfo bi = Introspector.getBeanInfo(C.class);

		assertThat(hasReadMethodForProperty(bi, "foo"), is(true));
		assertThat(hasWriteMethodForProperty(bi, "foo"), is(false));

		BeanInfo ebi = new ExtendedBeanInfo(bi);

		assertThat(hasReadMethodForProperty(bi, "foo"), is(true));
		assertThat(hasWriteMethodForProperty(bi, "foo"), is(false));

		assertThat(hasReadMethodForProperty(ebi, "foo"), is(true));
		assertThat(hasWriteMethodForProperty(ebi, "foo"), is(true));

		assertThat(ebi.getPropertyDescriptors().length, equalTo(bi.getPropertyDescriptors().length));
	}
