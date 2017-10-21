	@Nullable
	static String getAttributeOverrideName(Method attribute, @Nullable Class<? extends Annotation> metaAnnotationType) {
		Assert.notNull(attribute, "attribute must not be null");
		Assert.notNull(metaAnnotationType, "metaAnnotationType must not be null");
		Assert.isTrue(Annotation.class != metaAnnotationType,
				"metaAnnotationType must not be [java.lang.annotation.Annotation]");

		AliasDescriptor descriptor = AliasDescriptor.from(attribute);
		return (descriptor != null ? descriptor.getAttributeOverrideName(metaAnnotationType) : null);
	}
