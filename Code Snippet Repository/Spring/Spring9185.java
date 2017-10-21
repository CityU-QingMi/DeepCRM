	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ContentDisposition)) {
			return false;
		}
		ContentDisposition otherCd = (ContentDisposition) other;
		return (ObjectUtils.nullSafeEquals(this.type, otherCd.type) &&
				ObjectUtils.nullSafeEquals(this.name, otherCd.name) &&
				ObjectUtils.nullSafeEquals(this.filename, otherCd.filename) &&
				ObjectUtils.nullSafeEquals(this.charset, otherCd.charset) &&
				ObjectUtils.nullSafeEquals(this.size, otherCd.size) &&
				ObjectUtils.nullSafeEquals(this.creationDate, otherCd.creationDate)&&
				ObjectUtils.nullSafeEquals(this.modificationDate, otherCd.modificationDate)&&
				ObjectUtils.nullSafeEquals(this.readDate, otherCd.readDate));
	}
