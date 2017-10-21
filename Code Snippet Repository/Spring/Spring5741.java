	private SpelNodeImpl eatPossiblyQualifiedId() {
		LinkedList<SpelNodeImpl> qualifiedIdPieces = new LinkedList<>();
		Token node = peekToken();
		while (isValidQualifiedId(node)) {
			nextToken();
			if (node.kind != TokenKind.DOT) {
				qualifiedIdPieces.add(new Identifier(node.stringValue(), toPos(node)));
			}
			node = peekToken();
		}
		if (qualifiedIdPieces.isEmpty()) {
			if (node == null) {
				throw internalException( this.expressionString.length(), SpelMessage.OOD);
			}
			throw internalException(node.startPos, SpelMessage.NOT_EXPECTED_TOKEN,
					"qualified ID", node.getKind().toString().toLowerCase());
		}
		int pos = toPos(qualifiedIdPieces.getFirst().getStartPosition(),
				qualifiedIdPieces.getLast().getEndPosition());
		return new QualifiedIdentifier(pos,
				qualifiedIdPieces.toArray(new SpelNodeImpl[qualifiedIdPieces.size()]));
	}
