	private void testWithinPackage(boolean matchSubpackages) throws SecurityException, NoSuchMethodException {
		String withinBeansPackage = "within(org.springframework.tests.sample.beans.";
		// Subpackages are matched by **
		if (matchSubpackages) {
			withinBeansPackage += ".";
		}
		withinBeansPackage = withinBeansPackage + "*)";
		AspectJExpressionPointcut withinBeansPc = new AspectJExpressionPointcut();
		withinBeansPc.setExpression(withinBeansPackage);

		assertTrue(withinBeansPc.matches(TestBean.class));
		assertTrue(withinBeansPc.matches(getAge, TestBean.class));
		assertEquals(matchSubpackages, withinBeansPc.matches(DeepBean.class));
		assertEquals(matchSubpackages, withinBeansPc.matches(
				DeepBean.class.getMethod("aMethod", String.class), DeepBean.class));
		assertFalse(withinBeansPc.matches(String.class));
		assertFalse(withinBeansPc.matches(OtherIOther.class.getMethod("absquatulate"), OtherIOther.class));
	}
