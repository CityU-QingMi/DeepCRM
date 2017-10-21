	@Override
	public final void execute() {
		notifyObserversExplicitExecution();
		if ( getStatements().isEmpty() ) {
			return;
		}

		try {
			doExecuteBatch();
		}
		finally {
			releaseStatements();
		}
	}
