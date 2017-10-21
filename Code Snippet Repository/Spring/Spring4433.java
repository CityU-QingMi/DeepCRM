		@Nullable
		public String getAttributeOverrideName(Class<? extends Annotation> metaAnnotationType) {
			Assert.notNull(metaAnnotationType, "metaAnnotationType must not be null");
			Assert.isTrue(Annotation.class != metaAnnotationType,
					"metaAnnotationType must not be [java.lang.annotation.Annotation]");

			// Search the attribute override hierarchy, starting with the current attribute
			for (AliasDescriptor desc = this; desc != null; desc = desc.getAttributeOverrideDescriptor()) {
				if (desc.isOverrideFor(metaAnnotationType)) {
					return desc.aliasedAttributeName;
				}
			}

			// Else: explicit attribute override for a different meta-annotation
			return null;
		}
