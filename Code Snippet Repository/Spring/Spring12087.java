	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof AbstractNameValueExpression) {
			AbstractNameValueExpression<?> other = (AbstractNameValueExpression<?>) obj;
			String thisName = (isCaseSensitiveName() ? this.name : this.name.toLowerCase());
			String otherName = (isCaseSensitiveName() ? other.name : other.name.toLowerCase());
			return (thisName.equalsIgnoreCase(otherName) &&
					(this.value != null ? this.value.equals(other.value) : other.value == null) &&
					this.isNegated == other.isNegated);
		}
		return false;
	}
