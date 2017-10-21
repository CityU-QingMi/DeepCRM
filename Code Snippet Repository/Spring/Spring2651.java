	protected ScriptSource getScriptSource(String beanName, String scriptSourceLocator) {
		synchronized (this.scriptSourceCache) {
			ScriptSource scriptSource = this.scriptSourceCache.get(beanName);
			if (scriptSource == null) {
				scriptSource = convertToScriptSource(beanName, scriptSourceLocator, this.resourceLoader);
				this.scriptSourceCache.put(beanName, scriptSource);
			}
			return scriptSource;
		}
	}
