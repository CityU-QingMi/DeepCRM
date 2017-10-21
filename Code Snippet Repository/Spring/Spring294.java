	@Test
	@Ignore
	public void testIntroductionWithArgumentBinding() {
		TestBean target = new TestBean();

		List<Advisor> advisors = getFixture().getAdvisors(
				new SingletonMetadataAwareAspectInstanceFactory(new MakeITestBeanModifiable(),"someBean"));
		advisors.addAll(getFixture().getAdvisors(
				new SingletonMetadataAwareAspectInstanceFactory(new MakeLockable(),"someBean")));

		Modifiable modifiable = (Modifiable) createProxy(target,
				advisors,
				ITestBean.class);
		assertThat(modifiable, instanceOf(Modifiable.class));
		Lockable lockable = (Lockable) modifiable;
		assertFalse(lockable.locked());

		ITestBean itb = (ITestBean) modifiable;
		assertFalse(modifiable.isModified());
		int oldAge = itb.getAge();
		itb.setAge(oldAge + 1);
		assertTrue(modifiable.isModified());
		modifiable.acceptChanges();
		assertFalse(modifiable.isModified());
		itb.setAge(itb.getAge());
		assertFalse("Setting same value does not modify", modifiable.isModified());
		itb.setName("And now for something completely different");
		assertTrue(modifiable.isModified());

		lockable.lock();
		assertTrue(lockable.locked());
		try {
			itb.setName("Else");
			fail("Should be locked");
		}
		catch (IllegalStateException ex) {
			// Ok
		}
		lockable.unlock();
		itb.setName("Tony");
	}
