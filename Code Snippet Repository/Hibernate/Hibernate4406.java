	public TrimFunction(
			CriteriaBuilderImpl criteriaBuilder,
			Trimspec trimspec,
			char trimCharacter,
			Expression<String> trimSource) {
		super( criteriaBuilder, String.class, NAME );
		this.trimspec = trimspec;
		this.trimCharacter = new LiteralExpression<Character>( criteriaBuilder, trimCharacter );
		this.trimSource = trimSource;
	}
