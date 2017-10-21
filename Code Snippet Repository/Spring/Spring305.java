	@Test
	public void testMultiplePerTargetAspects() throws SecurityException, NoSuchMethodException {
		TestBean target = new TestBean();
		int realAge = 65;
		target.setAge(realAge);

		List<Advisor> advisors = new LinkedList<>();
		PerTargetAspect aspect1 = new PerTargetAspect();
		aspect1.count = 100;
		aspect1.setOrder(10);
		advisors.addAll(
				getFixture().getAdvisors(new SingletonMetadataAwareAspectInstanceFactory(aspect1, "someBean1")));
		PerTargetAspect aspect2 = new PerTargetAspect();
		aspect2.setOrder(5);
		advisors.addAll(
				getFixture().getAdvisors(new SingletonMetadataAwareAspectInstanceFactory(aspect2, "someBean2")));
		Collections.sort(advisors, new OrderComparator());

		TestBean itb = (TestBean) createProxy(target, advisors, TestBean.class);
		assertEquals("Around advice must NOT apply", realAge, itb.getAge());

		// Hit the method in the per clause to instantiate the aspect
		itb.getSpouse();

		assertEquals("Around advice must apply", 0, itb.getAge());
		assertEquals("Around advice must apply", 1, itb.getAge());
	}
