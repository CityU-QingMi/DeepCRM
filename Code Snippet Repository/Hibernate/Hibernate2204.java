	private TypeInfo(
			String typeName,
			int jdbcTypeCode,
			String[] createParams,
			boolean unsigned,
			int precision,
			short minimumScale,
			short maximumScale,
			boolean fixedPrecisionScale,
			String literalPrefix,
			String literalSuffix,
			boolean caseSensitive,
			TypeSearchability searchability,
			TypeNullability nullability) {
		this.typeName = typeName;
		this.jdbcTypeCode = jdbcTypeCode;
		this.createParams = createParams;
		this.unsigned = unsigned;
		this.precision = precision;
		this.minimumScale = minimumScale;
		this.maximumScale = maximumScale;
		this.fixedPrecisionScale = fixedPrecisionScale;
		this.literalPrefix = literalPrefix;
		this.literalSuffix = literalSuffix;
		this.caseSensitive = caseSensitive;
		this.searchability = searchability;
		this.nullability = nullability;
	}
