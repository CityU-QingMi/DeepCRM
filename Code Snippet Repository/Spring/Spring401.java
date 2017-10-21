	@Test
	public void testSerializableDelegatingIntroductionInterceptorSerializable() throws Exception {
		SerializablePerson serializableTarget = new SerializablePerson();
		String name = "Tony";
		serializableTarget.setName("Tony");

		ProxyFactory factory = new ProxyFactory(serializableTarget);
		factory.addInterface(Person.class);
		long time = 1000;
		TimeStamped ts = new SerializableTimeStamped(time);

		factory.addAdvisor(new DefaultIntroductionAdvisor(new DelegatingIntroductionInterceptor(ts)));
		factory.addAdvice(new SerializableNopInterceptor());

		Person p = (Person) factory.getProxy();

		assertEquals(name, p.getName());
		assertEquals(time, ((TimeStamped) p).getTimeStamp());

		Person p1 = (Person) SerializationTestUtils.serializeAndDeserialize(p);
		assertEquals(name, p1.getName());
		assertEquals(time, ((TimeStamped) p1).getTimeStamp());
	}
