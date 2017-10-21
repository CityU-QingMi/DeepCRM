	@Override
	public String render(Type argumentType, List args, SessionFactoryImplementor factory) throws QueryException {
		// first figure out if all arguments are dynamic (jdbc parameters) ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		boolean areAllArgumentsDynamic = true;
		for ( Object arg1 : args ) {
			final String arg = (String) arg1;
			if ( !"?".equals( arg ) ) {
				// we found a non-dynamic argument
				areAllArgumentsDynamic = false;
				break;
			}
		}

		if ( areAllArgumentsDynamic ) {
			return join(
					args.iterator(),
					CAST_STRING_TRANSFORMER,
					new StringJoinTemplate() {
						public String getBeginning() {
							return "varchar( ";
						}
						public String getSeparator() {
							return " || ";
						}
						public String getEnding() {
							return " )";
						}
					}
			);
		}
		else {
			return join(
					args.iterator(),
					NO_TRANSFORM_STRING_TRANSFORMER,
					new StringJoinTemplate() {
						public String getBeginning() {
							return "(";
						}
						public String getSeparator() {
							return "||";
						}
						public String getEnding() {
							return ")";
						}
					}
			);
		}
	}
