		@SuppressWarnings("")
		public ParameterMemento toMemento(SessionFactoryImpl sessionFactory) {
			final boolean initialPassNullSetting = explicitPassNullSetting != null
					? explicitPassNullSetting.booleanValue()
					: sessionFactory.getSessionFactoryOptions().isProcedureParameterNullPassingEnabled();

			return new ParameterMemento(
					position,
					name,
					parameterMode,
					type,
					sessionFactory.getTypeResolver().heuristicType( type.getName() ),
					initialPassNullSetting
			);
		}
