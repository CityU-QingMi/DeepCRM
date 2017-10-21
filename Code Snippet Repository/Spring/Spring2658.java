	protected Object evaluateScript(ScriptSource scriptSource) {
		try {
			ScriptEngine scriptEngine = this.scriptEngine;
			if (scriptEngine == null) {
				scriptEngine = retrieveScriptEngine(scriptSource);
				if (scriptEngine == null) {
					throw new IllegalStateException("Could not determine script engine for " + scriptSource);
				}
				this.scriptEngine = scriptEngine;
			}
			return scriptEngine.eval(scriptSource.getScriptAsString());
		}
		catch (Exception ex) {
			throw new ScriptCompilationException(scriptSource, ex);
		}
	}
