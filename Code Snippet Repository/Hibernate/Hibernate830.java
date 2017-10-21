	@Override
	public boolean usesJoinTable() {
		switch ( elementSource.getNature() ) {
			case BASIC:
			case AGGREGATE:
			case ONE_TO_MANY:
				return false;
			case MANY_TO_MANY:
				return true;
			case MANY_TO_ANY:
				throw new NotYetImplementedException(
						String.format( "%s is not implemented yet.", elementSource.getNature() )
				);
			default:
				throw new AssertionFailure(
						String.format(
								"Unexpected plural attribute element source nature: %s",
								elementSource.getNature()
						)
				);
		}
	}
