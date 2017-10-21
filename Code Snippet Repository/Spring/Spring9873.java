	private boolean processNumberedReference() {
		char referenceChar = this.originalMessage.charAt(this.nextPotentialReferencePosition + 2);
		boolean isHexNumberedReference = (referenceChar == 'x' || referenceChar == 'X');
		try {
			int value = (!isHexNumberedReference ?
					Integer.parseInt(getReferenceSubstring(2)) :
					Integer.parseInt(getReferenceSubstring(3), 16));
			this.decodedMessage.append((char) value);
			return true;
		}
		catch (NumberFormatException ex) {
			return false;
		}
	}
