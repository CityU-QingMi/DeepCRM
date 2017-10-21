	public SubstringFunction(
			CriteriaBuilderImpl criteriaBuilder,
			Expression<String> value,
			Expression<Integer> start,
			Expression<Integer> length) {
		super( criteriaBuilder, String.class, NAME );
		this.value = value;
		this.start = start;
		this.length = length;
	}
