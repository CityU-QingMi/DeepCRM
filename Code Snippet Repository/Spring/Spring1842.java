	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof JCacheOperationSourcePointcut)) {
			return false;
		}
		JCacheOperationSourcePointcut otherPc = (JCacheOperationSourcePointcut) other;
		return ObjectUtils.nullSafeEquals(getCacheOperationSource(), otherPc.getCacheOperationSource());
	}
