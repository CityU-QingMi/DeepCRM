	protected AbstractAttributeKey(AbstractAttributeKey parent, String property) {
		this.parent = parent;
		this.property = property;

		final String prefix;
		if ( parent != null ) {
			final String resolvedParent = parent.getFullPath();
			if ( StringHelper.isEmpty( resolvedParent ) ) {
				prefix = "";
			}
			else {
				prefix = resolvedParent + getDelimiter();
			}
			depth = parent.getDepth() + 1;
		}
		else {
			prefix = "";
			depth = 0;
		}

		this.fullPath = prefix + property;
	}
