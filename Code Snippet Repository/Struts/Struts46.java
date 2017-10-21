	public void validate() {
		if (iteratorValue1 == null || iteratorValue1.trim().length() <= 0) {
			addFieldError("iteratorValue1", "iterator value 1 cannot be empty");
		} else if (iteratorValue1.trim().indexOf(",") <= 0) {
			addFieldError("iteratorValue1", "iterator value 1 needs to be comma separated");
		}
		if (iteratorValue2 == null || iteratorValue2.trim().length() <= 0) {
			addFieldError("iteratorValue2", "iterator value 2 cannot be empty");
		} else if (iteratorValue2.trim().indexOf(",") <= 0) {
			addFieldError("iteratorValue2", "iterator value 2 needs to be comma separated");
		}
	}
