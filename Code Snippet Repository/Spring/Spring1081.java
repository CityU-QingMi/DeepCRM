	@Test
	public void shouldUseExtendedBeanInfoWhenApplicable() throws NoSuchMethodException, SecurityException {
		// given a class with a non-void returning setter method
		@SuppressWarnings("unused")
		class C {
			public Object setFoo(String s) { return this; }
			public String getFoo() { return null; }
		}

		// CachedIntrospectionResults should delegate to ExtendedBeanInfo
		CachedIntrospectionResults results = CachedIntrospectionResults.forClass(C.class);
		BeanInfo info = results.getBeanInfo();
		PropertyDescriptor pd = null;
		for (PropertyDescriptor candidate : info.getPropertyDescriptors()) {
			if (candidate.getName().equals("foo")) {
				pd = candidate;
			}
		}

		// resulting in a property descriptor including the non-standard setFoo method
		assertThat(pd, notNullValue());
		assertThat(pd.getReadMethod(), equalTo(C.class.getMethod("getFoo")));
		assertThat(
				"No write method found for non-void returning 'setFoo' method. " +
				"Check to see if CachedIntrospectionResults is delegating to " +
				"ExtendedBeanInfo as expected",
				pd.getWriteMethod(), equalTo(C.class.getMethod("setFoo", String.class)));
	}
