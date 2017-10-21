	private boolean maybeEatMethodOrProperty(boolean nullSafeNavigation) {
		if (peekToken(TokenKind.IDENTIFIER)) {
			Token methodOrPropertyName = takeToken();
			SpelNodeImpl[] args = maybeEatMethodArgs();
			if (args == null) {
				// property
				push(new PropertyOrFieldReference(nullSafeNavigation, methodOrPropertyName.stringValue(),
						toPos(methodOrPropertyName)));
				return true;
			}
			// method reference
			push(new MethodReference(nullSafeNavigation, methodOrPropertyName.stringValue(),
					toPos(methodOrPropertyName), args));
			// TODO what is the end position for a method reference? the name or the last arg?
			return true;
		}
		return false;
	}
