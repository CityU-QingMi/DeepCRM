	@Test
	public void requestContextListenerWithSameThread() {
		RequestContextListener listener = new RequestContextListener();
		MockServletContext context = new MockServletContext();
		MockHttpServletRequest request = new MockHttpServletRequest(context);
		request.setAttribute("test", "value");

		assertNull(RequestContextHolder.getRequestAttributes());
		listener.requestInitialized(new ServletRequestEvent(context, request));
		assertNotNull(RequestContextHolder.getRequestAttributes());
		assertEquals("value",
				RequestContextHolder.getRequestAttributes().getAttribute("test", RequestAttributes.SCOPE_REQUEST));
		MockRunnable runnable = new MockRunnable();
		RequestContextHolder.getRequestAttributes().registerDestructionCallback(
				"test", runnable, RequestAttributes.SCOPE_REQUEST);

		listener.requestDestroyed(new ServletRequestEvent(context, request));
		assertNull(RequestContextHolder.getRequestAttributes());
		assertTrue(runnable.wasExecuted());
	}
