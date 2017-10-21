	public int getCorrespondingLobCode(int jdbcTypeCode) {
		Integer lobTypeCode = lobCodeByNonLobCode.get( jdbcTypeCode );
		if ( lobTypeCode == null ) {
			throw new IllegalArgumentException(
					String.format(
							Locale.ROOT,
							"JDBC type-code [%s (%s)] not known to have a corresponding LOB equivalent",
							jdbcTypeCode,
							JdbcTypeNameMapper.getTypeName( jdbcTypeCode )
					)
			);
		}
		return lobTypeCode;
	}
