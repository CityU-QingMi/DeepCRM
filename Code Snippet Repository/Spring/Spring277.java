	@Test
	public void testMatchGenericArgument() {
		String expression = "execution(* set*(java.util.List<org.springframework.tests.sample.beans.TestBean>) )";
		AspectJExpressionPointcut ajexp = new AspectJExpressionPointcut();
		ajexp.setExpression(expression);

		// TODO this will currently map, would be nice for optimization
		//assertTrue(ajexp.matches(HasGeneric.class));
		//assertFalse(ajexp.matches(TestBean.class));

		Method takesGenericList = methodsOnHasGeneric.get("setFriends");
		assertTrue(ajexp.matches(takesGenericList, HasGeneric.class));
		assertTrue(ajexp.matches(methodsOnHasGeneric.get("setEnemies"), HasGeneric.class));
		assertFalse(ajexp.matches(methodsOnHasGeneric.get("setPartners"), HasGeneric.class));
		assertFalse(ajexp.matches(methodsOnHasGeneric.get("setPhoneNumbers"), HasGeneric.class));

		assertFalse(ajexp.matches(getAge, TestBean.class));
	}
