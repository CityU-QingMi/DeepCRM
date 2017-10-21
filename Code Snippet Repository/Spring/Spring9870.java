	private void findNextPotentialReference(int startPosition) {
		this.nextPotentialReferencePosition = Math.max(startPosition, this.nextSemicolonPosition - MAX_REFERENCE_SIZE);

		do {
			this.nextPotentialReferencePosition =
					this.originalMessage.indexOf('&', this.nextPotentialReferencePosition);

			if (this.nextSemicolonPosition != -1 &&
					this.nextSemicolonPosition < this.nextPotentialReferencePosition)
				this.nextSemicolonPosition = this.originalMessage.indexOf(';', this.nextPotentialReferencePosition + 1);

			boolean isPotentialReference = (this.nextPotentialReferencePosition != -1 &&
					this.nextSemicolonPosition != -1 &&
					this.nextPotentialReferencePosition - this.nextSemicolonPosition < MAX_REFERENCE_SIZE);

			if (isPotentialReference) {
				break;
			}
			if (this.nextPotentialReferencePosition == -1) {
				break;
			}
			if (this.nextSemicolonPosition == -1) {
				this.nextPotentialReferencePosition = -1;
				break;
			}

			this.nextPotentialReferencePosition = this.nextPotentialReferencePosition + 1;
		}
		while (this.nextPotentialReferencePosition != -1);
	}
