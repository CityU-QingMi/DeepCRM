	@Override
	protected Class<?>[] getAnnotatedClasses() {
		return new Class[] {
				MappedGrandparentEntity.class,
				MappedParentEntity.class,
				StrIntTestEntity.class,
				ChildCompleteEntity.class,
				BabyCompleteEntity.class
		};
	}
