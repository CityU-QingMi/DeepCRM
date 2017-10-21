	private void processNaturalIdUniqueKeyBinders() {
		if ( naturalIdUniqueKeyBinderMap == null ) {
			return;
		}

		for ( NaturalIdUniqueKeyBinder naturalIdUniqueKeyBinder : naturalIdUniqueKeyBinderMap.values() ) {
			naturalIdUniqueKeyBinder.process();
		}

		naturalIdUniqueKeyBinderMap.clear();
	}
