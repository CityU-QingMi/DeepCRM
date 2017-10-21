	public String getDisplayValue() {
		if (this.value instanceof String) {
			return (String) this.value;
		}
		if (this.value != null) {
			return (this.htmlEscape ?
					HtmlUtils.htmlEscape(this.value.toString()) : this.value.toString());
		}
		return "";
	}
