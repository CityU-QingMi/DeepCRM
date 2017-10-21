	@Test
	public void testCanChangeArgumentsIndependentlyOnClonedInvocation() throws Throwable {
		TestBean tb = new TestBean();
		ProxyFactory pc = new ProxyFactory(tb);
		pc.addInterface(ITestBean.class);

/**/
/**/
/**/
		MethodInterceptor nameReverter = new MethodInterceptor() {
			@Override
			public Object invoke(MethodInvocation mi) throws Throwable {
				MethodInvocation clone = ((ReflectiveMethodInvocation) mi).invocableClone();
				String oldName = ((ITestBean) mi.getThis()).getName();
				clone.getArguments()[0] = oldName;
				// Original method invocation should be unaffected by changes to argument list of clone
				mi.proceed();
				return clone.proceed();
			}
		};

		class NameSaver implements MethodInterceptor {
			private List<Object> names = new LinkedList<>();

			@Override
			public Object invoke(MethodInvocation mi) throws Throwable {
				names.add(mi.getArguments()[0]);
				return mi.proceed();
			}
		}

		NameSaver saver = new NameSaver();

		pc.addAdvisor(new DefaultPointcutAdvisor(Pointcuts.SETTERS, nameReverter));
		pc.addAdvisor(new DefaultPointcutAdvisor(Pointcuts.SETTERS, saver));
		ITestBean it = (ITestBean) createProxy(pc);

		String name1 = "tony";
		String name2 = "gordon";

		tb.setName(name1);
		assertEquals(name1, tb.getName());

		it.setName(name2);
		// NameReverter saved it back
		assertEquals(name1, it.getName());
		assertEquals(2, saver.names.size());
		assertEquals(name2, saver.names.get(0));
		assertEquals(name1, saver.names.get(1));
	}
