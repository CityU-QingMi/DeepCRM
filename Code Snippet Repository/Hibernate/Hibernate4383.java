	public void validateBindValue(Object value) {
		if ( value == null ) {
			return;
		}

		if ( ! getParameterType().isInstance( value ) ) {
			if ( isNamed() ) {
				throw new IllegalArgumentException(
						String.format(
								"Named parameter [%s] type mismatch; expecting [%s] but found [%s]",
								getName(),
								getParameterType().getSimpleName(),
								value.getClass().getSimpleName()
						)
				);
			}
			else {
				throw new IllegalArgumentException(
						String.format(
								"Positional parameter [%s] type mismatch; expecting [%s] but found [%s]",
								getPosition(),
								getParameterType().getSimpleName(),
								value.getClass().getSimpleName()
						)
				);
			}
		}
	}
