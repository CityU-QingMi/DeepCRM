	@Test
	public void toStringOutput() throws IntrospectionException, SecurityException, NoSuchMethodException {
		{
			Object pd = new ExtendedBeanInfo.SimplePropertyDescriptor("foo", null, null);
			assertThat(pd.toString(), containsString(
					"PropertyDescriptor[name=foo, propertyType=null, readMethod=null"));
		}
		{
			class C {
				@SuppressWarnings("unused")
				public Object setFoo(String foo) { return null; }
			}
			Method m = C.class.getMethod("setFoo", String.class);
			Object pd = new ExtendedBeanInfo.SimplePropertyDescriptor("foo", null, m);
			assertThat(pd.toString(), allOf(
					containsString("PropertyDescriptor[name=foo"),
					containsString("propertyType=class java.lang.String"),
					containsString("readMethod=null, writeMethod=public java.lang.Object")));
		}
		{
			Object pd = new ExtendedBeanInfo.SimpleIndexedPropertyDescriptor("foo", null, null, null, null);
			assertThat(pd.toString(), containsString(
					"PropertyDescriptor[name=foo, propertyType=null, indexedPropertyType=null"));
		}
		{
			class C {
				@SuppressWarnings("unused")
				public Object setFoo(int i, String foo) { return null; }
			}
			Method m = C.class.getMethod("setFoo", int.class, String.class);
			Object pd = new ExtendedBeanInfo.SimpleIndexedPropertyDescriptor("foo", null, null, null, m);
			assertThat(pd.toString(), allOf(
					containsString("PropertyDescriptor[name=foo, propertyType=null"),
					containsString("indexedPropertyType=class java.lang.String"),
					containsString("indexedWriteMethod=public java.lang.Object")));
		}
	}
