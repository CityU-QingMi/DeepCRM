	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (obj instanceof TestEntity) {
			return ObjectUtils.nullSafeEquals(this.id, ((TestEntity) obj).id);
		}
		return false;
	}
