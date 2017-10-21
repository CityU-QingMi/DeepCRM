	@Test
	public void toShortAndLongStringFormedCorrectly() throws Exception {
		final Object raw = new TestBean();
		ProxyFactory pf = new ProxyFactory(raw);
		pf.addAdvisor(ExposeInvocationInterceptor.ADVISOR);
		pf.addAdvice(new MethodBeforeAdvice() {
			@Override
			public void before(Method method, Object[] args, @Nullable Object target) throws Throwable {
				// makeEncSJP, although meant for computing the enclosing join point,
				// it serves our purpose here
				JoinPoint.StaticPart aspectJVersionJp = Factory.makeEncSJP(method);
				JoinPoint jp = AbstractAspectJAdvice.currentJoinPoint();

				assertEquals(aspectJVersionJp.getSignature().toLongString(), jp.getSignature().toLongString());
				assertEquals(aspectJVersionJp.getSignature().toShortString(), jp.getSignature().toShortString());
				assertEquals(aspectJVersionJp.getSignature().toString(), jp.getSignature().toString());

				assertEquals(aspectJVersionJp.toLongString(), jp.toLongString());
				assertEquals(aspectJVersionJp.toShortString(), jp.toShortString());
				assertEquals(aspectJVersionJp.toString(), jp.toString());
			}
		});
		ITestBean itb = (ITestBean) pf.getProxy();
		itb.getAge();
		itb.setName("foo");
		itb.getDoctor();
		itb.getStringArray();
		itb.getSpouse();
		itb.setSpouse(new TestBean());
		try {
			itb.unreliableFileOperation();
		}
		catch (IOException ex) {
			// we don't realy care...
		}
	}
