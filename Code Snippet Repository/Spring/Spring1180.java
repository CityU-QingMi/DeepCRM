	@Test
	public void testAliasCircle() {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		lbf.registerAlias("test", "test2");
		lbf.registerAlias("test2", "test3");

		try {
			lbf.registerAlias("test3", "test2");
			fail("Should have thrown IllegalStateException");
		}
		catch (IllegalStateException ex) {
			// expected
		}

		try {
			lbf.registerAlias("test3", "test");
			fail("Should have thrown IllegalStateException");
		}
		catch (IllegalStateException ex) {
			// expected
		}

		lbf.registerAlias("test", "test3");
	}
