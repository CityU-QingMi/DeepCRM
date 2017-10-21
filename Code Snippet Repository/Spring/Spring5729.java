	private SpelNodeImpl eatDottedNode() {
		Token t = takeToken();  // it was a '.' or a '?.'
		boolean nullSafeNavigation = (t.kind == TokenKind.SAFE_NAVI);
		if (maybeEatMethodOrProperty(nullSafeNavigation) || maybeEatFunctionOrVar() ||
				maybeEatProjection(nullSafeNavigation) || maybeEatSelection(nullSafeNavigation)) {
			return pop();
		}
		if (peekToken() == null) {
			// unexpectedly ran out of data
			throw internalException(t.startPos, SpelMessage.OOD);
		}
		else {
			throw internalException(t.startPos, SpelMessage.UNEXPECTED_DATA_AFTER_DOT, toString(peekToken()));
		}
	}
