	@Test
	public void testMultiplePerTargetAspectsWithOrderAnnotation() throws SecurityException, NoSuchMethodException {
		TestBean target = new TestBean();
		int realAge = 65;
		target.setAge(realAge);

		List<Advisor> advisors = new LinkedList<>();
		PerTargetAspectWithOrderAnnotation10 aspect1 = new PerTargetAspectWithOrderAnnotation10();
		aspect1.count = 100;
		advisors.addAll(
				getFixture().getAdvisors(new SingletonMetadataAwareAspectInstanceFactory(aspect1, "someBean1")));
		PerTargetAspectWithOrderAnnotation5 aspect2 = new PerTargetAspectWithOrderAnnotation5();
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
