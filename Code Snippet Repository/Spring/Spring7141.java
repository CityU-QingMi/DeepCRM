	@Override
	public boolean equals(@Nullable Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj != null && getClass() == obj.getClass()) {
			AbstractMessageCondition<?> other = (AbstractMessageCondition<?>) obj;
			return getContent().equals(other.getContent());
		}
		return false;
	}
