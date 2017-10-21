	@Test
	public void testIntroductionOnTargetNotImplementingInterface() {
		NotLockable notLockableTarget = new NotLockable();
		assertFalse(notLockableTarget instanceof Lockable);
		NotLockable notLockable1 = (NotLockable) createProxy(notLockableTarget,
				getFixture().getAdvisors(
						new SingletonMetadataAwareAspectInstanceFactory(new MakeLockable(),"someBean")),
				NotLockable.class);
		assertTrue(notLockable1 instanceof Lockable);
		Lockable lockable = (Lockable) notLockable1;
		assertFalse(lockable.locked());
		lockable.lock();
		assertTrue(lockable.locked());

		NotLockable notLockable2Target = new NotLockable();
		NotLockable notLockable2 = (NotLockable) createProxy(notLockable2Target,
				getFixture().getAdvisors(
						new SingletonMetadataAwareAspectInstanceFactory(new MakeLockable(),"someBean")),
				NotLockable.class);
		assertTrue(notLockable2 instanceof Lockable);
		Lockable lockable2 = (Lockable) notLockable2;
		assertFalse(lockable2.locked());
		notLockable2.setIntValue(1);
		lockable2.lock();
		try {
			notLockable2.setIntValue(32);
			fail();
		}
		catch (IllegalStateException ex) {
		}
		assertTrue(lockable2.locked());
	}
