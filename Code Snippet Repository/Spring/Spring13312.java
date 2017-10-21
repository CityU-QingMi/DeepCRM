		@Override
		public Dynamic addFilter(String filterName, Filter filter) {
			if (filters.containsKey(filterName)) {
				return null;
			}
			filters.put(filterName, filter);
			MockFilterRegistration registration = new MockFilterRegistration();
			filterRegistrations.put(filterName, registration);
			return registration;
		}
