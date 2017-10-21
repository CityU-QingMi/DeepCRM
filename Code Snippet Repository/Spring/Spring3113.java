	@Test
	public void noCacheResolved() {
		Method method = ReflectionUtils.findMethod(SimpleService.class, "noCacheResolved", Object.class);
		try {
			this.simpleService.noCacheResolved(new Object());
			fail("Should have failed, no cache resolved");
		}
		catch (IllegalStateException ex) {
			assertTrue("Reference to the method must be contained in the message", ex.getMessage().contains(method.toString()));
		}
	}
