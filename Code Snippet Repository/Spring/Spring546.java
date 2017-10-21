		@Override
		public String buildErrorMessage() {
			String propertyName = getPropertyName();
			String[] possibleMatches = getPossibleMatches();
			StringBuilder msg = new StringBuilder();
			msg.append("Bean property '");
			msg.append(propertyName);
			msg.append("' has no matching field. ");

			if (!ObjectUtils.isEmpty(possibleMatches)) {
				appendHintMessage(msg);
			}
			return msg.toString();
		}
