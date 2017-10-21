	public void clearRegistrations() {
		nameUuidXref.clear();
		for ( SessionFactory factory : sessionFactoryMap.values() ) {
			try {
				factory.close();
			}
			catch (Exception ignore) {
			}
		}
		sessionFactoryMap.clear();
	}
