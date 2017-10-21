	private CallbackType toCallbackType(Class annotationClass) {
		if ( annotationClass == CallbackType.POST_LOAD.getCallbackAnnotation() ) {
			return CallbackType.POST_LOAD;
		}
		else if ( annotationClass == CallbackType.PRE_PERSIST.getCallbackAnnotation() ) {
			return CallbackType.PRE_PERSIST;
		}
		else if ( annotationClass == CallbackType.POST_PERSIST.getCallbackAnnotation() ) {
			return CallbackType.POST_PERSIST;
		}
		else if ( annotationClass == CallbackType.PRE_UPDATE.getCallbackAnnotation() ) {
			return CallbackType.PRE_UPDATE;
		}
		else if ( annotationClass == CallbackType.POST_UPDATE.getCallbackAnnotation() ) {
			return CallbackType.POST_UPDATE;
		}
		else if ( annotationClass == CallbackType.PRE_REMOVE.getCallbackAnnotation() ) {
			return CallbackType.PRE_REMOVE;
		}
		else if ( annotationClass == CallbackType.POST_REMOVE.getCallbackAnnotation() ) {
			return CallbackType.POST_REMOVE;
		}

		throw new PersistenceException( "Unrecognized JPA callback annotation [" + annotationClass + "]" );
	}
