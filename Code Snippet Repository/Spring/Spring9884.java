	public static boolean hasAncestorOfType(Tag tag, Class<?> ancestorTagClass) {
		Assert.notNull(tag, "Tag cannot be null");
		Assert.notNull(ancestorTagClass, "Ancestor tag class cannot be null");
		if (!Tag.class.isAssignableFrom(ancestorTagClass)) {
			throw new IllegalArgumentException(
					"Class '" + ancestorTagClass.getName() + "' is not a valid Tag type");
		}
		Tag ancestor = tag.getParent();
		while (ancestor != null) {
			if (ancestorTagClass.isAssignableFrom(ancestor.getClass())) {
				return true;
			}
			ancestor = ancestor.getParent();
		}
		return false;
	}
