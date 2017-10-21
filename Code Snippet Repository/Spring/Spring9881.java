	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof OpaqueUriComponents)) {
			return false;
		}

		OpaqueUriComponents other = (OpaqueUriComponents) obj;
		return ObjectUtils.nullSafeEquals(getScheme(), other.getScheme()) &&
				ObjectUtils.nullSafeEquals(this.ssp, other.ssp) &&
				ObjectUtils.nullSafeEquals(getFragment(), other.getFragment());

	}
