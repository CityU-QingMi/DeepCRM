	@Override
	@Nullable
	public Object getScriptedObject(ScriptSource scriptSource, @Nullable Class<?>... actualInterfaces)
			throws IOException, ScriptCompilationException {

		Object script = evaluateScript(scriptSource);

		if (!ObjectUtils.isEmpty(actualInterfaces)) {
			boolean adaptationRequired = false;
			for (Class<?> requestedIfc : actualInterfaces) {
				if (script instanceof Class ? !requestedIfc.isAssignableFrom((Class<?>) script) :
						!requestedIfc.isInstance(script)) {
					adaptationRequired = true;
				}
			}
			if (adaptationRequired) {
				script = adaptToInterfaces(script, scriptSource, actualInterfaces);
			}
		}

		if (script instanceof Class) {
			Class<?> scriptClass = (Class<?>) script;
			try {
				return ReflectionUtils.accessibleConstructor(scriptClass).newInstance();
			}
			catch (NoSuchMethodException ex) {
				throw new ScriptCompilationException(
						"No default constructor on script class: " + scriptClass.getName(), ex);
			}
			catch (InstantiationException ex) {
				throw new ScriptCompilationException(
						scriptSource, "Unable to instantiate script class: " + scriptClass.getName(), ex);
			}
			catch (IllegalAccessException ex) {
				throw new ScriptCompilationException(
						scriptSource, "Could not access script constructor: " + scriptClass.getName(), ex);
			}
			catch (InvocationTargetException ex) {
				throw new ScriptCompilationException(
						"Failed to invoke script constructor: " + scriptClass.getName(), ex.getTargetException());
			}
		}

		return script;
	}
