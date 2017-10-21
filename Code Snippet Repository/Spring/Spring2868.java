	@Test
	public void testSingletonInstancesAreEqual() {
		ITestBean test1 = (ITestBean) factory.getBean("test1");
		ITestBean test1_1 = (ITestBean) factory.getBean("test1");
		//assertTrue("Singleton instances ==", test1 == test1_1);
		assertEquals("Singleton instances ==", test1, test1_1);
		test1.setAge(25);
		assertEquals(test1.getAge(), test1_1.getAge());
		test1.setAge(250);
		assertEquals(test1.getAge(), test1_1.getAge());
		Advised pc1 = (Advised) test1;
		Advised pc2 = (Advised) test1_1;
		assertArrayEquals(pc1.getAdvisors(), pc2.getAdvisors());
		int oldLength = pc1.getAdvisors().length;
		NopInterceptor di = new NopInterceptor();
		pc1.addAdvice(1, di);
		assertArrayEquals(pc1.getAdvisors(), pc2.getAdvisors());
		assertEquals("Now have one more advisor", oldLength + 1, pc2.getAdvisors().length);
		assertEquals(di.getCount(), 0);
		test1.setAge(5);
		assertEquals(test1_1.getAge(), test1.getAge());
		assertEquals(di.getCount(), 3);
	}
