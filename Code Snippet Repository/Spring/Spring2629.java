	@Override
	@Nullable
	public Class<?> getScriptedObjectType(ScriptSource scriptSource)
			throws IOException, ScriptCompilationException {

		synchronized (this.scriptClassMonitor) {
			try {
				if (scriptSource.isModified()) {
					// New script content: Let's check whether it evaluates to a Class.
					this.wasModifiedForTypeCheck = true;
					this.scriptClass = BshScriptUtils.determineBshObjectType(
							scriptSource.getScriptAsString(), this.beanClassLoader);
				}
				return this.scriptClass;
			}
			catch (EvalError ex) {
				this.scriptClass = null;
				throw new ScriptCompilationException(scriptSource, ex);
			}
		}
	}
