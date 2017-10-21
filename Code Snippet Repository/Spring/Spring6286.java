	@Override
	protected Object resolveSpecifiedLookupKey(Object lookupKey) {
		if (lookupKey instanceof Integer) {
			return lookupKey;
		}
		else if (lookupKey instanceof String) {
			String constantName = (String) lookupKey;
			if (!constantName.startsWith(DefaultTransactionDefinition.PREFIX_ISOLATION)) {
				throw new IllegalArgumentException("Only isolation constants allowed");
			}
			return constants.asNumber(constantName);
		}
		else {
			throw new IllegalArgumentException(
					"Invalid lookup key - needs to be isolation level Integer or isolation level name String: " + lookupKey);
		}
	}
