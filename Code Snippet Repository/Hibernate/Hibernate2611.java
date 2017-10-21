	private Type resolveDateTimeArithmeticResultType(Type lhType, Type rhType) {
		// here, we work under the following assumptions:
		//      ------------ valid cases --------------------------------------
		//      1) datetime + {something other than datetime} : always results
		//              in a datetime ( db will catch invalid conversions )
		//      2) datetime - datetime : always results in a DOUBLE
		//      3) datetime - {something other than datetime} : always results
		//              in a datetime ( db will catch invalid conversions )
		//      ------------ invalid cases ------------------------------------
		//      4) datetime + datetime
		//      5) {something other than datetime} - datetime
		//      6) datetime * {any type}
		//      7) datetime / {any type}
		//      8) {any type} / datetime
		// doing so allows us to properly handle parameters as either the left
		// or right side here in the majority of cases
		boolean lhsIsDateTime = isDateTimeType( lhType );
		boolean rhsIsDateTime = isDateTimeType( rhType );

		// handle the (assumed) valid cases:
		// #1 - the only valid datetime addition synatx is one or the other is a datetime (but not both)
		if ( getType() == HqlSqlTokenTypes.PLUS ) {
			// one or the other needs to be a datetime for us to get into this method in the first place...
			return lhsIsDateTime ? lhType : rhType;
		}
		else if ( getType() == HqlSqlTokenTypes.MINUS ) {
			// #3 - note that this is also true of "datetime - :param"...
			if ( lhsIsDateTime && !rhsIsDateTime ) {
				return lhType;
			}
			// #2
			if ( lhsIsDateTime && rhsIsDateTime ) {
				return StandardBasicTypes.DOUBLE;
			}
		}
		return null;
	}
