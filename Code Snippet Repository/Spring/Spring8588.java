	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Person)) {
			return false;
		}
		Person otherPerson = (Person) other;
		return (ObjectUtils.nullSafeEquals(this.name, otherPerson.name) &&
				ObjectUtils.nullSafeEquals(this.someDouble, otherPerson.someDouble) &&
				ObjectUtils.nullSafeEquals(this.someBoolean, otherPerson.someBoolean));
	}
