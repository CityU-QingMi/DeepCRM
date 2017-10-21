	@Override
	public String extractConstraintName(SQLException sqle) {
		try {
			String constraintName = null;

			// handle nested exceptions
			do {
				constraintName = doExtractConstraintName(sqle);
				if (sqle.getNextException() == null
						|| sqle.getNextException() == sqle) {
					break;
				}
				else {
					sqle = sqle.getNextException();
				}
			} while (constraintName == null);

			return constraintName;
		}
		catch (NumberFormatException nfe) {
			return null;
		}
	}
