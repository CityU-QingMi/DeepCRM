	@Test
	public void circleLeadsToException() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(requestAttributes);

		try {
			String name = "requestScopedObjectCircle1";
			assertNull(request.getAttribute(name));

			this.beanFactory.getBean(name);
			fail("Should have thrown BeanCreationException");
		}
		catch (BeanCreationException ex) {
			assertTrue(ex.contains(BeanCurrentlyInCreationException.class));
		}
	}
