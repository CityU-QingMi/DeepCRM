	private void doTestDestructionWithSessionSerialization(boolean beanNameReset) throws Exception {
		Serializable serializedState = null;

		MockHttpSession session = new MockHttpSession();
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setSession(session);
		ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);

		RequestContextHolder.setRequestAttributes(requestAttributes);
		String name = "sessionScopedDisposableObject";
		assertNull(session.getAttribute(name));
		DerivedTestBean bean = (DerivedTestBean) this.beanFactory.getBean(name);
		assertEquals(session.getAttribute(name), bean);
		assertSame(bean, this.beanFactory.getBean(name));

		requestAttributes.requestCompleted();
		serializedState = session.serializeState();
		assertFalse(bean.wasDestroyed());

		serializedState = (Serializable) SerializationTestUtils.serializeAndDeserialize(serializedState);

		session = new MockHttpSession();
		session.deserializeState(serializedState);
		request = new MockHttpServletRequest();
		request.setSession(session);
		requestAttributes = new ServletRequestAttributes(request);

		RequestContextHolder.setRequestAttributes(requestAttributes);
		name = "sessionScopedDisposableObject";
		assertNotNull(session.getAttribute(name));
		bean = (DerivedTestBean) this.beanFactory.getBean(name);
		assertEquals(session.getAttribute(name), bean);
		assertSame(bean, this.beanFactory.getBean(name));

		requestAttributes.requestCompleted();
		session.invalidate();
		assertTrue(bean.wasDestroyed());

		if (beanNameReset) {
			assertNull(bean.getBeanName());
		}
		else {
			assertNotNull(bean.getBeanName());
		}
	}
