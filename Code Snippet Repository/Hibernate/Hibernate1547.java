	private static ValidationMode getMode(String modeProperty) {
		if (modeProperty == null || modeProperty.length() == 0) {
			return AUTO;
		}
		else {
			try {
				return valueOf( modeProperty.trim().toUpperCase(Locale.ROOT) );
			}
			catch ( IllegalArgumentException e ) {
				throw new HibernateException( "Unknown validation mode in " + BeanValidationIntegrator.MODE_PROPERTY + ": " + modeProperty );
			}
		}
	}
