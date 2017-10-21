	@Test
	public void testResolveOverloadedSignature() throws Exception {
		// test resolve with no args
		Method desiredMethod = MethodSignatureBean.class.getMethod("overloaded");
		assertSignatureEquals(desiredMethod, "overloaded()");

		// resolve with single arg
		desiredMethod = MethodSignatureBean.class.getMethod("overloaded", new Class[]{String.class});
		assertSignatureEquals(desiredMethod, "overloaded(java.lang.String)");

		// resolve with two args
		desiredMethod = MethodSignatureBean.class.getMethod("overloaded", new Class[]{String.class, BeanFactory.class});
		assertSignatureEquals(desiredMethod, "overloaded(java.lang.String, org.springframework.beans.factory.BeanFactory)");
	}
