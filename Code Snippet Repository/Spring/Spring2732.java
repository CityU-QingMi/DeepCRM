	public void captureFloatArgument(JoinPoint tjp, float arg) {
		float tjpArg = ((Float) tjp.getArgs()[0]).floatValue();
		if (Math.abs(tjpArg - arg) > 0.000001) {
			throw new IllegalStateException(
					"argument is '" + arg + "', " +
					"but args array has '" + tjpArg + "'"
					);
		}
		this.lastBeforeFloatValue = arg;
	}
