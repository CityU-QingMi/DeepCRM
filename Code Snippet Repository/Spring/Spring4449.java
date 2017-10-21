	public static SynthesizingMethodParameter forExecutable(Executable executable, int parameterIndex) {
		if (executable instanceof Method) {
			return new SynthesizingMethodParameter((Method) executable, parameterIndex);
		}
		else if (executable instanceof Constructor) {
			return new SynthesizingMethodParameter((Constructor<?>) executable, parameterIndex);
		}
		else {
			throw new IllegalArgumentException("Not a Method/Constructor: " + executable);
		}
	}
