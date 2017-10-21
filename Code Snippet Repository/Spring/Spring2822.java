		@Override
		public Object invoke(MethodInvocation mi) throws Throwable {
			String task = "get invocation on way IN";
			try {
				MethodInvocation current = ExposeInvocationInterceptor.currentInvocation();
				assertEquals(mi.getMethod(), current.getMethod());
				Object retval = mi.proceed();
				task = "get invocation on way OUT";
				assertEquals(current, ExposeInvocationInterceptor.currentInvocation());
				return retval;
			}
			catch (IllegalStateException ex) {
				System.err.println(task + " for " + mi.getMethod());
				ex.printStackTrace();
				throw ex;
			}
		}
