	@Rule
	ExternalResource getResource2() {
		return new ExternalResource() {
			@Override
			protected void before() throws Throwable {
				beforeEvents.add("methodRule");
			}

			@Override
			protected void after() {
				afterEvents.add("methodRule");
			}
		};
	}
