	@Test
	public void indexedEquality() throws IntrospectionException, SecurityException, NoSuchMethodException {
		Object pd1 = new ExtendedBeanInfo.SimpleIndexedPropertyDescriptor("foo", null, null, null, null);
		assertThat(pd1, equalTo(pd1));

		Object pd2 = new ExtendedBeanInfo.SimpleIndexedPropertyDescriptor("foo", null, null, null, null);
		assertThat(pd1, equalTo(pd2));
		assertThat(pd2, equalTo(pd1));

		@SuppressWarnings("unused")
		class C {
			public Object setFoo(int i, String foo) { return null; }
			public String getFoo(int i) { return null; }
		}
		Method wm1 = C.class.getMethod("setFoo", int.class, String.class);
		Object pd3 = new ExtendedBeanInfo.SimpleIndexedPropertyDescriptor("foo", null, null, null, wm1);
		assertThat(pd1, not(equalTo(pd3)));
		assertThat(pd3, not(equalTo(pd1)));

		Method rm1 = C.class.getMethod("getFoo", int.class);
		Object pd4 = new ExtendedBeanInfo.SimpleIndexedPropertyDescriptor("foo", null, null, rm1, null);
		assertThat(pd1, not(equalTo(pd4)));
		assertThat(pd4, not(equalTo(pd1)));

		Object pd5 = new IndexedPropertyDescriptor("foo", null, null, null, null);
		assertThat(pd1, equalTo(pd5));
		assertThat(pd5, equalTo(pd1));

		Object pd6 = "not a PD";
		assertThat(pd1, not(equalTo(pd6)));
		assertThat(pd6, not(equalTo(pd1)));

		Object pd7 = null;
		assertThat(pd1, not(equalTo(pd7)));
		assertThat(pd7, not(equalTo(pd1)));
	}
