	@After
	public void after() {
		if (enabled != null) {
			System.setProperty("ENABLED", enabled);
		}
		else {
			System.clearProperty("ENABLED");
		}
		if (context != null) {
			context.close();
		}
	}
