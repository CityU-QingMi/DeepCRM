		@Override
		public Type getReturnType(Type firstArgumentType, Mapping mapping) {
			final int jdbcType = determineJdbcTypeCode( firstArgumentType, mapping );

			// First allow the actual type to control the return value; the underlying sqltype could
			// actually be different
			if ( firstArgumentType == StandardBasicTypes.BIG_INTEGER ) {
				return StandardBasicTypes.BIG_INTEGER;
			}
			else if ( firstArgumentType == StandardBasicTypes.BIG_DECIMAL ) {
				return StandardBasicTypes.BIG_DECIMAL;
			}
			else if ( firstArgumentType == StandardBasicTypes.LONG
					|| firstArgumentType == StandardBasicTypes.SHORT
					|| firstArgumentType == StandardBasicTypes.INTEGER ) {
				return StandardBasicTypes.LONG;
			}
			else if ( firstArgumentType == StandardBasicTypes.FLOAT || firstArgumentType == StandardBasicTypes.DOUBLE)  {
				return StandardBasicTypes.DOUBLE;
			}

			// finally use the jdbcType if == on Hibernate types did not find a match.
			//
			//	IMPL NOTE : we do not match on Types.NUMERIC because it could be either, so we fall-through to the
			// 		first argument type
			if ( jdbcType == Types.FLOAT
					|| jdbcType == Types.DOUBLE
					|| jdbcType == Types.DECIMAL
					|| jdbcType == Types.REAL) {
				return StandardBasicTypes.DOUBLE;
			}
			else if ( jdbcType == Types.BIGINT
					|| jdbcType == Types.INTEGER
					|| jdbcType == Types.SMALLINT
					|| jdbcType == Types.TINYINT ) {
				return StandardBasicTypes.LONG;
			}

			// as a last resort, return the type of the first argument
			return firstArgumentType;
		}
