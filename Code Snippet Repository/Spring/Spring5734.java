	private boolean maybeEatBeanReference() {
		if (peekToken(TokenKind.BEAN_REF) || peekToken(TokenKind.FACTORY_BEAN_REF)) {
			Token beanRefToken = takeToken();
			Token beanNameToken = null;
			String beanName = null;
			if (peekToken(TokenKind.IDENTIFIER)) {
				beanNameToken = eatToken(TokenKind.IDENTIFIER);
				beanName = beanNameToken.stringValue();
			}
			else if (peekToken(TokenKind.LITERAL_STRING)) {
				beanNameToken = eatToken(TokenKind.LITERAL_STRING);
				beanName = beanNameToken.stringValue();
				beanName = beanName.substring(1, beanName.length() - 1);
			}
			else {
				throw internalException(beanRefToken.startPos, SpelMessage.INVALID_BEAN_REFERENCE);
			}
			BeanReference beanReference;
			if (beanRefToken.getKind() == TokenKind.FACTORY_BEAN_REF) {
				String beanNameString = String.valueOf(TokenKind.FACTORY_BEAN_REF.tokenChars) + beanName;
				beanReference = new BeanReference(
						toPos(beanRefToken.startPos, beanNameToken.endPos), beanNameString);
			}
			else {
				beanReference = new BeanReference(toPos(beanNameToken), beanName);
			}
			this.constructedNodes.push(beanReference);
			return true;
		}
		return false;
	}
