	protected ScriptEngine getEngine() {
		if (Boolean.FALSE.equals(this.sharedEngine)) {
			Map<Object, ScriptEngine> engines = enginesHolder.get();
			if (engines == null) {
				engines = new HashMap<>(4);
				enginesHolder.set(engines);
			}
			Assert.state(this.engineName != null, "No engine name specified");
			Object engineKey = (!ObjectUtils.isEmpty(this.scripts) ?
					new EngineKey(this.engineName, this.scripts) : this.engineName);
			ScriptEngine engine = engines.get(engineKey);
			if (engine == null) {
				engine = createEngineFromName(engineName);
				engines.put(engineKey, engine);
			}
			return engine;
		}
		else {
			// Simply return the configured ScriptEngine...
			Assert.state(this.engine != null, "No shared engine available");
			return this.engine;
		}
	}
