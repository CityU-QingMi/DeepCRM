	@Test
	public void testIntroductionOnTargetExcludedByTypePattern() {
		LinkedList<Object> target = new LinkedList<>();
		List<?> proxy = (List<?>) createProxy(target,
				AopUtils.findAdvisorsThatCanApply(
						getFixture().getAdvisors(new SingletonMetadataAwareAspectInstanceFactory(new MakeLockable(), "someBean")),
						List.class
				),
				CannotBeUnlocked.class);
		assertFalse("Type pattern must have excluded mixin", proxy instanceof Lockable);
	}
