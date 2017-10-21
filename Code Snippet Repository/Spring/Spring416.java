	@Test
	public void testSerialization() throws Throwable {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(bf).loadBeanDefinitions(CONTEXT);
		// This is a CGLIB proxy, so we can proxy it to the target class
		Person p = (Person) bf.getBean("serializableSettersAdvised");
		// Interceptor behind regexp advisor
		NopInterceptor nop = (NopInterceptor) bf.getBean("nopInterceptor");
		assertEquals(0, nop.getCount());

		int newAge = 12;
		// Not advised
		assertEquals(0, p.getAge());
		assertEquals(0, nop.getCount());

		// This is proxied
		p.setAge(newAge);
		assertEquals(1, nop.getCount());
		p.setAge(newAge);
		assertEquals(newAge, p.getAge());
		// Only setter fired
		assertEquals(2, nop.getCount());

		// Serialize and continue...
		p = (Person) SerializationTestUtils.serializeAndDeserialize(p);
		assertEquals(newAge, p.getAge());
		// Remembers count, but we need to get a new reference to nop...
		nop = (SerializableNopInterceptor) ((Advised) p).getAdvisors()[0].getAdvice();
		assertEquals(2, nop.getCount());
		assertEquals("serializableSettersAdvised", p.getName());
		p.setAge(newAge + 1);
		assertEquals(3, nop.getCount());
		assertEquals(newAge + 1, p.getAge());
	}
