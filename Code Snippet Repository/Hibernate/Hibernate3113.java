	private void initializeFromSessionOwner(SessionOwner sessionOwner) {
		if ( sessionOwner != null ) {
			if ( sessionOwner.getExceptionMapper() != null ) {
				exceptionMapper = sessionOwner.getExceptionMapper();
			}
			else {
				exceptionMapper = ExceptionMapperStandardImpl.INSTANCE;
			}
			if ( sessionOwner.getAfterCompletionAction() != null ) {
				afterCompletionAction = sessionOwner.getAfterCompletionAction();
			}
			else {
				afterCompletionAction = STANDARD_AFTER_COMPLETION_ACTION;
			}
			if ( sessionOwner.getManagedFlushChecker() != null ) {
				managedFlushChecker = sessionOwner.getManagedFlushChecker();
			}
			else {
				managedFlushChecker = STANDARD_MANAGED_FLUSH_CHECKER;
			}
		}
		else {
			exceptionMapper = ExceptionMapperStandardImpl.INSTANCE;
			afterCompletionAction = STANDARD_AFTER_COMPLETION_ACTION;
			managedFlushChecker = STANDARD_MANAGED_FLUSH_CHECKER;
		}
	}
