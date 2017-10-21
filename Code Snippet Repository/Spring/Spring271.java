	private static boolean matches(final String beanName, String pcExpression) {
		@SuppressWarnings("serial")
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut() {
			@Override
			protected String getCurrentProxiedBeanName() {
				return beanName;
			}
		};
		pointcut.setExpression(pcExpression);
		return pointcut.matches(TestBean.class);
	}
