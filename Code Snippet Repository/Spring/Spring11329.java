	protected ScriptEngine getEngine() {
		if (Boolean.FALSE.equals(this.sharedEngine)) {
			Assert.state(this.engineName != null, "No engine name specified");
			return createEngineFromName(this.engineName);
		}
		else {
			Assert.state(this.engine != null, "No shared engine available");
			return this.engine;
		}
	}
