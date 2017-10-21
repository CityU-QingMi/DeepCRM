	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		ScriptEngineManager scriptEngineManager = this.scriptEngineManager;
		if (scriptEngineManager == null) {
			scriptEngineManager = new ScriptEngineManager(classLoader);
			this.scriptEngineManager = scriptEngineManager;
			Bindings bindings = this.globalBindings;
			if (bindings != null) {
				scriptEngineManager.setBindings(bindings);
			}
		}
	}
