	@SuppressWarnings("")
	public Iterable<StrategyRegistration> getStrategyRegistrations() {
		return Collections.singletonList(
				(StrategyRegistration) new SimpleStrategyRegistrationImpl(
						Calendar.class,
						GregorianCalendar.class,
						GREGORIAN
				)
		);
	}
