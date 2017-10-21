	private void testThisOrTarget(String which) throws SecurityException, NoSuchMethodException {
		String matchesTestBean = which + "(org.springframework.tests.sample.beans.TestBean)";
		String matchesIOther = which + "(org.springframework.tests.sample.beans.IOther)";
		AspectJExpressionPointcut testBeanPc = new AspectJExpressionPointcut();
		testBeanPc.setExpression(matchesTestBean);

		AspectJExpressionPointcut iOtherPc = new AspectJExpressionPointcut();
		iOtherPc.setExpression(matchesIOther);

		assertTrue(testBeanPc.matches(TestBean.class));
		assertTrue(testBeanPc.matches(getAge, TestBean.class));
		assertTrue(iOtherPc.matches(OtherIOther.class.getMethod("absquatulate"), OtherIOther.class));
		assertFalse(testBeanPc.matches(OtherIOther.class.getMethod("absquatulate"), OtherIOther.class));
	}
