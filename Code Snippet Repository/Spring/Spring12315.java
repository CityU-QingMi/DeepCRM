	private String[] initErrorMessages() throws NoSuchMessageException {
		if (this.errorMessages == null) {
			if (this.objectErrors != null) {
				this.errorMessages = new String[this.objectErrors.size()];
				for (int i = 0; i < this.objectErrors.size(); i++) {
					ObjectError error = this.objectErrors.get(i);
					this.errorMessages[i] = this.requestContext.getMessage(error, this.htmlEscape);
				}
			}
			else {
				this.errorMessages = new String[0];
			}
		}
		return this.errorMessages;
	}
