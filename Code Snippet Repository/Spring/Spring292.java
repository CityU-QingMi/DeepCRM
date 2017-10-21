	@Test
	public void testIntroductionOnTargetImplementingInterface() {
		CannotBeUnlocked target = new CannotBeUnlocked();
		Lockable proxy = (Lockable) createProxy(target,
				// Ensure that we exclude
				AopUtils.findAdvisorsThatCanApply(
						getFixture().getAdvisors(
								new SingletonMetadataAwareAspectInstanceFactory(new MakeLockable(), "someBean")),
						CannotBeUnlocked.class
				),
				CannotBeUnlocked.class);
		assertThat(proxy, instanceOf(Lockable.class));
		Lockable lockable = proxy;
		assertTrue("Already locked", lockable.locked());
		lockable.lock();
		assertTrue("Real target ignores locking", lockable.locked());
		try {
			lockable.unlock();
			fail();
		}
		catch (UnsupportedOperationException ex) {
			// Ok
		}
	}
