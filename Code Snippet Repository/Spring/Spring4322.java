	public ReactiveAdapterRegistry() {

		// Reactor
		boolean reactorRegistered = false;
		try {
			new ReactorRegistrar().registerAdapters(this);
			reactorRegistered = true;
		}
		catch (Throwable ex) {
			// Ignore
		}
		this.reactorPresent = reactorRegistered;

		// RxJava1
		try {
			new RxJava1Registrar().registerAdapters(this);
		}
		catch (Throwable ex) {
			// Ignore
		}

		// RxJava2
		try {
			new RxJava2Registrar().registerAdapters(this);
		}
		catch (Throwable ex) {
			// Ignore
		}
	}
