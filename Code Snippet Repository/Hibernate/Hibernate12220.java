	@Test
	@WithClasses({ Base.class, EmbeddableEntity.class, IStuff.class, MyEntity.class, Stuff.class })
	public void testCorrectAccessTypeUsedForEmbeddable() {
		assertAttributeTypeInMetaModelFor(
				EmbeddableEntity.class,
				"stuffs",
				Stuff.class,
				"The target annotation set the type to Stuff"
		);
	}
