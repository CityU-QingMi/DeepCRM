	public void validateCalendarBind() {
		if ( ! Calendar.class.isAssignableFrom( getParameterType() ) ) {
			if ( isNamed() ) {
				throw new IllegalArgumentException(
						String.format(
								"Named parameter [%s] type mismatch; Calendar was passed, but parameter defined as [%s]",
								getName(),
								getParameterType().getSimpleName()
						)
				);
			}
			else {
				throw new IllegalArgumentException(
						String.format(
								"Positional parameter [%s] type mismatch; Calendar was passed, but parameter defined as [%s]",
								getPosition(),
								getParameterType().getSimpleName()
						)
				);
			}
		}
	}
