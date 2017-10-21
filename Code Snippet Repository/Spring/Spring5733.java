	@Nullable
	private SpelNodeImpl eatStartNode() {
		if (maybeEatLiteral()) {
			return pop();
		}
		else if (maybeEatParenExpression()) {
			return pop();
		}
		else if (maybeEatTypeReference() || maybeEatNullReference() || maybeEatConstructorReference() ||
				maybeEatMethodOrProperty(false) || maybeEatFunctionOrVar()) {
			return pop();
		}
		else if (maybeEatBeanReference()) {
			return pop();
		}
		else if (maybeEatProjection(false) || maybeEatSelection(false) || maybeEatIndexer()) {
			return pop();
		}
		else if (maybeEatInlineListOrMap()) {
			return pop();
		}
		else {
			return null;
		}
	}
