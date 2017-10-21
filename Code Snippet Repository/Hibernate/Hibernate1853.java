	public AnsiTrimEmulationFunction(String ltrimFunctionName, String rtrimFunctionName, String replaceFunctionName) {
		leadingSpaceTrim = new SQLFunctionTemplate(
				StandardBasicTypes.STRING,
				LEADING_SPACE_TRIM_TEMPLATE.replaceAll( LTRIM, ltrimFunctionName )
		);

		trailingSpaceTrim = new SQLFunctionTemplate(
				StandardBasicTypes.STRING,
				TRAILING_SPACE_TRIM_TEMPLATE.replaceAll( RTRIM, rtrimFunctionName )
		);

		bothSpaceTrim = new SQLFunctionTemplate(
				StandardBasicTypes.STRING,
				BOTH_SPACE_TRIM_TEMPLATE.replaceAll( LTRIM, ltrimFunctionName )
						.replaceAll( RTRIM, rtrimFunctionName )
		);

		bothSpaceTrimFrom = new SQLFunctionTemplate(
				StandardBasicTypes.STRING,
				BOTH_SPACE_TRIM_FROM_TEMPLATE.replaceAll( LTRIM, ltrimFunctionName )
						.replaceAll( RTRIM, rtrimFunctionName )
		);

		leadingTrim = new SQLFunctionTemplate(
				StandardBasicTypes.STRING,
				LEADING_TRIM_TEMPLATE.replaceAll( LTRIM, ltrimFunctionName )
						.replaceAll( RTRIM, rtrimFunctionName )
						.replaceAll( REPLACE,replaceFunctionName )
		);

		trailingTrim = new SQLFunctionTemplate(
				StandardBasicTypes.STRING,
				TRAILING_TRIM_TEMPLATE.replaceAll( LTRIM, ltrimFunctionName )
						.replaceAll( RTRIM, rtrimFunctionName )
						.replaceAll( REPLACE,replaceFunctionName )
		);

		bothTrim = new SQLFunctionTemplate(
				StandardBasicTypes.STRING,
				BOTH_TRIM_TEMPLATE.replaceAll( LTRIM, ltrimFunctionName )
						.replaceAll( RTRIM, rtrimFunctionName )
						.replaceAll( REPLACE,replaceFunctionName )
		);
	}
