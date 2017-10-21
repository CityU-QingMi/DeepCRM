	private void assertActiveBundle(String symbolicName) {
		for (Bundle bundle : bundleContext.getBundles()) {
			if (bundle.getSymbolicName().equals( symbolicName )) {
				Assert.assertEquals(
						symbolicName + " was found, but not in an ACTIVE state.", Bundle.ACTIVE, bundle.getState());
				return;
			}
		}
		Assert.fail("Could not find bundle: " + symbolicName);
	}
