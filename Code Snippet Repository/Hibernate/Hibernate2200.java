	public void walkWarnings(
			SQLWarning warning,
			WarningHandler handler) {
		if ( warning == null || !handler.doProcess() ) {
			return;
		}
		handler.prepare( warning );
		while ( warning != null ) {
			handler.handleWarning( warning );
			warning = warning.getNextWarning();
		}
	}
