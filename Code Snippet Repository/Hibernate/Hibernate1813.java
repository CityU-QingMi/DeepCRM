	private Class locateOracleTypesClass() {
		try {
			return ReflectHelper.classForName( ORACLE_TYPES_CLASS_NAME );
		}
		catch (ClassNotFoundException e) {
			try {
				return ReflectHelper.classForName( DEPRECATED_ORACLE_TYPES_CLASS_NAME );
			}
			catch (ClassNotFoundException e2) {
				throw new HibernateException(
						String.format(
								"Unable to locate OracleTypes class using either known FQN [%s, %s]",
								ORACLE_TYPES_CLASS_NAME,
								DEPRECATED_ORACLE_TYPES_CLASS_NAME
						),
						e
				);
			}
		}
	}
