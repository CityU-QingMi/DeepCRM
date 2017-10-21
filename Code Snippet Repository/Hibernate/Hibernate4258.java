		public static ParameterMemento fromRegistration(ParameterRegistrationImplementor registration) {
			return new ParameterMemento(
					registration.getPosition(),
					registration.getName(),
					registration.getMode(),
					registration.getType(),
					registration.getHibernateType(),
					registration.isPassNullsEnabled()
			);
		}
