	private static Serializable extractIdentifier(ResultSet rs, String identifier, Type type, Class clazz)
			throws SQLException {
		if ( clazz == Long.class ) {
			return rs.getLong( identifier );
		}
		else if ( clazz == Integer.class ) {
			return rs.getInt( identifier );
		}
		else if ( clazz == Short.class ) {
			return rs.getShort( identifier );
		}
		else if ( clazz == String.class ) {
			return rs.getString( identifier );
		}
		else if ( clazz == BigInteger.class ) {
			return rs.getBigDecimal( identifier ).setScale( 0, BigDecimal.ROUND_UNNECESSARY ).toBigInteger();
		}
		else if ( clazz == BigDecimal.class ) {
			return rs.getBigDecimal( identifier ).setScale( 0, BigDecimal.ROUND_UNNECESSARY );
		}
		else {
			throw new IdentifierGenerationException(
					"unrecognized id type : " + type.getName() + " -> " + clazz.getName()
			);
		}
	}
