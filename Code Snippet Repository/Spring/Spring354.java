	@Test
	public void testGetsAllInterfaces() throws Exception {
		// Extend to get new interface
		class TestBeanSubclass extends TestBean implements Comparable<Object> {
			@Override
			public int compareTo(Object arg0) {
				throw new UnsupportedOperationException("compareTo");
			}
		}
		TestBeanSubclass raw = new TestBeanSubclass();
		ProxyFactory factory = new ProxyFactory(raw);
		//System.out.println("Proxied interfaces are " + StringUtils.arrayToDelimitedString(factory.getProxiedInterfaces(), ","));
		assertEquals("Found correct number of interfaces", 5, factory.getProxiedInterfaces().length);
		ITestBean tb = (ITestBean) factory.getProxy();
		assertThat("Picked up secondary interface", tb, instanceOf(IOther.class));

		raw.setAge(25);
		assertTrue(tb.getAge() == raw.getAge());

		long t = 555555L;
		TimestampIntroductionInterceptor ti = new TimestampIntroductionInterceptor(t);

		Class<?>[] oldProxiedInterfaces = factory.getProxiedInterfaces();

		factory.addAdvisor(0, new DefaultIntroductionAdvisor(ti, TimeStamped.class));

		Class<?>[] newProxiedInterfaces = factory.getProxiedInterfaces();
		assertEquals("Advisor proxies one more interface after introduction", oldProxiedInterfaces.length + 1, newProxiedInterfaces.length);

		TimeStamped ts = (TimeStamped) factory.getProxy();
		assertTrue(ts.getTimeStamp() == t);
		// Shouldn't fail;
		 ((IOther) ts).absquatulate();
	}
