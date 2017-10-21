	@Test
	public void requestContextListenerWithDifferentThread() {
		final RequestContextListener listener = new RequestContextListener();
		final MockServletContext context = new MockServletContext();
		final MockHttpServletRequest request = new MockHttpServletRequest(context);
		request.setAttribute("test", "value");

		assertNull(RequestContextHolder.getRequestAttributes());
		listener.requestInitialized(new ServletRequestEvent(context, request));
		assertNotNull(RequestContextHolder.getRequestAttributes());
		assertEquals("value",
				RequestContextHolder.getRequestAttributes().getAttribute("test", RequestAttributes.SCOPE_REQUEST));
		MockRunnable runnable = new MockRunnable();
		RequestContextHolder.getRequestAttributes().registerDestructionCallback(
				"test", runnable, RequestAttributes.SCOPE_REQUEST);

		// Execute requestDestroyed callback in different thread.
		Thread thread = new Thread() {
			@Override
			public void run() {
				listener.requestDestroyed(new ServletRequestEvent(context, request));
			}
		};
		thread.start();
		try {
			thread.join();
		}
		catch (InterruptedException ex) {
		}
		// Still bound to original thread, but at least completed.
		assertNotNull(RequestContextHolder.getRequestAttributes());
		assertTrue(runnable.wasExecuted());

		// Check that a repeated execution in the same thread works and performs cleanup.
		listener.requestInitialized(new ServletRequestEvent(context, request));
		listener.requestDestroyed(new ServletRequestEvent(context, request));
		assertNull(RequestContextHolder.getRequestAttributes());
	}
