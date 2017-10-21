		@Override
		public String buildErrorMessage() {
			String propertyName = getPropertyName();
			String[] possibleMatches = getPossibleMatches();
			StringBuilder msg = new StringBuilder();
			msg.append("Bean property '");
			msg.append(propertyName);
			msg.append("' is not writable or has an invalid setter method. ");

			if (ObjectUtils.isEmpty(possibleMatches)) {
				msg.append("Does the parameter type of the setter match the return type of the getter?");
			}
			else {
				appendHintMessage(msg);
			}
			return msg.toString();
		}
