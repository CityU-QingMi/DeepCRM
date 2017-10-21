	private boolean maybeEatFunctionOrVar() {
		if (!peekToken(TokenKind.HASH)) {
			return false;
		}
		Token t = takeToken();
		Token functionOrVariableName = eatToken(TokenKind.IDENTIFIER);
		SpelNodeImpl[] args = maybeEatMethodArgs();
		if (args == null) {
			push(new VariableReference(functionOrVariableName.stringValue(),
					toPos(t.startPos, functionOrVariableName.endPos)));
			return true;
		}

		push(new FunctionReference(functionOrVariableName.stringValue(),
				toPos(t.startPos, functionOrVariableName.endPos), args));
		return true;
	}
