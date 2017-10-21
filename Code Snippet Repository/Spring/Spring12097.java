	private static Collection<HeaderExpression> parseExpressions(String... headers) {
		Set<HeaderExpression> expressions = new LinkedHashSet<>();
		for (String header : headers) {
			HeaderExpression expr = new HeaderExpression(header);
			if ("Accept".equalsIgnoreCase(expr.name) || "Content-Type".equalsIgnoreCase(expr.name)) {
				continue;
			}
			expressions.add(expr);
		}
		return expressions;
	}
