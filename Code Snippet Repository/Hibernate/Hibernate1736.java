	public String getHibernateTypeName(int code, int length, int precision, int scale) throws HibernateException {
		final String result = hibernateTypeNames.get( code, length, precision, scale );
		if ( result == null ) {
			throw new HibernateException(
					String.format(
							"No Hibernate type mapping for type [code=%s, length=%s]",
							code,
							length
					)
			);
		}
		return result;
	}
