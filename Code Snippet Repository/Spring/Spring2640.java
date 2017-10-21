	@Nullable
	protected Object executeScript(ScriptSource scriptSource, Class<?> scriptClass) throws ScriptCompilationException {
		try {
			GroovyObject goo = (GroovyObject) ReflectionUtils.accessibleConstructor(scriptClass).newInstance();

			if (this.groovyObjectCustomizer != null) {
				// Allow metaclass and other customization.
				this.groovyObjectCustomizer.customize(goo);
			}

			if (goo instanceof Script) {
				// A Groovy script, probably creating an instance: let's execute it.
				return ((Script) goo).run();
			}
			else {
				// An instance of the scripted class: let's return it as-is.
				return goo;
			}
		}
		catch (NoSuchMethodException ex) {
			throw new ScriptCompilationException(
					"No default constructor on Groovy script class: " + scriptClass.getName(), ex);
		}
		catch (InstantiationException ex) {
			throw new ScriptCompilationException(
					scriptSource, "Unable to instantiate Groovy script class: " + scriptClass.getName(), ex);
		}
		catch (IllegalAccessException ex) {
			throw new ScriptCompilationException(
					scriptSource, "Could not access Groovy script constructor: " + scriptClass.getName(), ex);
		}
		catch (InvocationTargetException ex) {
			throw new ScriptCompilationException(
					"Failed to invoke Groovy script constructor: " + scriptClass.getName(), ex.getTargetException());
		}
	}
