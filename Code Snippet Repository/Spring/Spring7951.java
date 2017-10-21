	@Test
	public void testOpenEntityManagerInViewInterceptorAsyncTimeoutScenario() throws Exception {

		// Initial request thread

		OpenEntityManagerInViewInterceptor interceptor = new OpenEntityManagerInViewInterceptor();
		interceptor.setEntityManagerFactory(factory);

		given(this.factory.createEntityManager()).willReturn(this.manager);

		interceptor.preHandle(this.webRequest);
		assertTrue(TransactionSynchronizationManager.hasResource(this.factory));

		AsyncWebRequest asyncWebRequest = new StandardServletAsyncWebRequest(this.request, this.response);
		WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(this.request);
		asyncManager.setTaskExecutor(new SyncTaskExecutor());
		asyncManager.setAsyncWebRequest(asyncWebRequest);
		asyncManager.startCallableProcessing(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "anything";
			}
		});

		interceptor.afterConcurrentHandlingStarted(this.webRequest);
		assertFalse(TransactionSynchronizationManager.hasResource(this.factory));

		// Async request timeout

		given(this.manager.isOpen()).willReturn(true);

		MockAsyncContext asyncContext = (MockAsyncContext) this.request.getAsyncContext();
		for (AsyncListener listener : asyncContext.getListeners()) {
			listener.onTimeout(new AsyncEvent(asyncContext));
		}
		for (AsyncListener listener : asyncContext.getListeners()) {
			listener.onComplete(new AsyncEvent(asyncContext));
		}

		verify(this.manager).close();
	}
