	private void copyCharactersTillPotentialReference() {
		if (this.nextPotentialReferencePosition != this.currentPosition) {
			int skipUntilIndex = (this.nextPotentialReferencePosition != -1 ?
					this.nextPotentialReferencePosition : this.originalMessage.length());
			if (skipUntilIndex - this.currentPosition > 3) {
				this.decodedMessage.append(this.originalMessage.substring(this.currentPosition, skipUntilIndex));
				this.currentPosition = skipUntilIndex;
			}
			else {
				while (this.currentPosition < skipUntilIndex)
					this.decodedMessage.append(this.originalMessage.charAt(this.currentPosition++));
			}
		}
	}
