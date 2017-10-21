	private void processPossibleReference() {
		if (this.nextPotentialReferencePosition != -1) {
			boolean isNumberedReference = (this.originalMessage.charAt(this.currentPosition + 1) == '#');
			boolean wasProcessable = isNumberedReference ? processNumberedReference() : processNamedReference();
			if (wasProcessable) {
				this.currentPosition = this.nextSemicolonPosition + 1;
			}
			else {
				char currentChar = this.originalMessage.charAt(this.currentPosition);
				this.decodedMessage.append(currentChar);
				this.currentPosition++;
			}
		}
	}
