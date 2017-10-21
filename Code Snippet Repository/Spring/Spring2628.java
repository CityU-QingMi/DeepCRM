	@Override
	@Nullable
	public Object evaluate(ScriptSource script, @Nullable Map<String, Object> arguments) {
		try {
			Interpreter interpreter = new Interpreter();
			interpreter.setClassLoader(this.classLoader);
			if (arguments != null) {
				for (Map.Entry<String, Object> entry : arguments.entrySet()) {
					interpreter.set(entry.getKey(), entry.getValue());
				}
			}
			return interpreter.eval(new StringReader(script.getScriptAsString()));
		}
		catch (IOException ex) {
			throw new ScriptCompilationException(script, "Cannot access BeanShell script", ex);
		}
		catch (EvalError ex) {
			throw new ScriptCompilationException(script, ex);
		}
	}
