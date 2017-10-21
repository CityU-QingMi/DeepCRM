	@Test
	public void testPointcutCanNeverApply() {
		class TestPointcut extends StaticMethodMatcherPointcut {
			@Override
			public boolean matches(Method method, @Nullable Class<?> clazzy) {
				return false;
			}
		}

		Pointcut no = new TestPointcut();
		assertFalse(AopUtils.canApply(no, Object.class));
	}
