	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasLength(text)) {
			// tokenize it with ","
			String[] tokens = StringUtils.commaDelimitedListToStringArray(text);
			RuleBasedTransactionAttribute attr = new RuleBasedTransactionAttribute();
			for (int i = 0; i < tokens.length; i++) {
				// Trim leading and trailing whitespace.
				String token = StringUtils.trimWhitespace(tokens[i].trim());
				// Check whether token contains illegal whitespace within text.
				if (StringUtils.containsWhitespace(token)) {
					throw new IllegalArgumentException(
							"Transaction attribute token contains illegal whitespace: [" + token + "]");
				}
				// Check token type.
				if (token.startsWith(RuleBasedTransactionAttribute.PREFIX_PROPAGATION)) {
					attr.setPropagationBehaviorName(token);
				}
				else if (token.startsWith(RuleBasedTransactionAttribute.PREFIX_ISOLATION)) {
					attr.setIsolationLevelName(token);
				}
				else if (token.startsWith(RuleBasedTransactionAttribute.PREFIX_TIMEOUT)) {
					String value = token.substring(DefaultTransactionAttribute.PREFIX_TIMEOUT.length());
					attr.setTimeout(Integer.parseInt(value));
				}
				else if (token.equals(RuleBasedTransactionAttribute.READ_ONLY_MARKER)) {
					attr.setReadOnly(true);
				}
				else if (token.startsWith(RuleBasedTransactionAttribute.PREFIX_COMMIT_RULE)) {
					attr.getRollbackRules().add(new NoRollbackRuleAttribute(token.substring(1)));
				}
				else if (token.startsWith(RuleBasedTransactionAttribute.PREFIX_ROLLBACK_RULE)) {
					attr.getRollbackRules().add(new RollbackRuleAttribute(token.substring(1)));
				}
				else {
					throw new IllegalArgumentException("Invalid transaction attribute token: [" + token + "]");
				}
			}
			setValue(attr);
		}
		else {
			setValue(null);
		}
	}
