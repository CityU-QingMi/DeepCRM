	private static Matcher<BeanDefinitionRegistry> containsBeanDefinition(final String beanName) {
		return new TypeSafeMatcher<BeanDefinitionRegistry>() {

			@Override
			public void describeTo(Description desc) {
				desc.appendText("a BeanDefinitionRegistry containing bean named ")
					.appendValue(beanName);
			}

			@Override
			public boolean matchesSafely(BeanDefinitionRegistry beanFactory) {
				return beanFactory.containsBeanDefinition(beanName);
			}

		};
	}
