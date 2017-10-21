	@Test
	public void testSerialization() throws Exception {
		SerializablePerson sp1 = new SerializablePerson();
		sp1.setName("Tony");
		SerializablePerson sp2 = new SerializablePerson();
		sp1.setName("Gordon");

		HotSwappableTargetSource hts = new HotSwappableTargetSource(sp1);
		ProxyFactory pf = new ProxyFactory();
		pf.addInterface(Person.class);
		pf.setTargetSource(hts);
		pf.addAdvisor(new DefaultPointcutAdvisor(new SerializableNopInterceptor()));
		Person p = (Person) pf.getProxy();

		assertEquals(sp1.getName(), p.getName());
		hts.swap(sp2);
		assertEquals(sp2.getName(), p.getName());

		p = (Person) SerializationTestUtils.serializeAndDeserialize(p);
		// We need to get a reference to the client-side targetsource
		hts = (HotSwappableTargetSource) ((Advised) p).getTargetSource();
		assertEquals(sp2.getName(), p.getName());
		hts.swap(sp1);
		assertEquals(sp1.getName(), p.getName());

	}
