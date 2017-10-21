	private boolean maybeEatConstructorReference() {
		if (peekIdentifierToken("new")) {
			Token newToken = takeToken();
			// It looks like a constructor reference but is NEW being used as a map key?
			if (peekToken(TokenKind.RSQUARE)) {
				// looks like 'NEW]' (so NEW used as map key)
				push(new PropertyOrFieldReference(false, newToken.stringValue(), toPos(newToken)));
				return true;
			}
			SpelNodeImpl possiblyQualifiedConstructorName = eatPossiblyQualifiedId();
			List<SpelNodeImpl> nodes = new ArrayList<>();
			nodes.add(possiblyQualifiedConstructorName);
			if (peekToken(TokenKind.LSQUARE)) {
				// array initializer
				List<SpelNodeImpl> dimensions = new ArrayList<>();
				while (peekToken(TokenKind.LSQUARE, true)) {
					if (!peekToken(TokenKind.RSQUARE)) {
						dimensions.add(eatExpression());
					}
					else {
						dimensions.add(null);
					}
					eatToken(TokenKind.RSQUARE);
				}
				if (maybeEatInlineListOrMap()) {
					nodes.add(pop());
				}
				push(new ConstructorReference(toPos(newToken),
						dimensions.toArray(new SpelNodeImpl[dimensions.size()]),
						nodes.toArray(new SpelNodeImpl[nodes.size()])));
			}
			else {
				// regular constructor invocation
				eatConstructorArgs(nodes);
				// TODO correct end position?
				push(new ConstructorReference(toPos(newToken),
						nodes.toArray(new SpelNodeImpl[nodes.size()])));
			}
			return true;
		}
		return false;
	}
