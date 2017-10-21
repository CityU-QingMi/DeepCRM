	@Override
	public int hashCode() {
		int result = ObjectUtils.nullSafeHashCode(this.type);
		result = 31 * result + ObjectUtils.nullSafeHashCode(this.name);
		result = 31 * result + ObjectUtils.nullSafeHashCode(this.filename);
		result = 31 * result + ObjectUtils.nullSafeHashCode(this.charset);
		result = 31 * result + ObjectUtils.nullSafeHashCode(this.size);
		result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
		result = 31 * result + (modificationDate != null ? modificationDate.hashCode() : 0);
		result = 31 * result + (readDate != null ? readDate.hashCode() : 0);
		return result;
	}
