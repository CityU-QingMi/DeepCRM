	private boolean resolveAsNakedComponentPropertyRefRHS(DotNode parent) {
		FromElement fromElement = locateSingleFromElement();
		if (fromElement == null) {
			return false;
		}

		Type propertyType;
		String propertyPath = parent.getLhs().getText() + "." + getText();
		try {
			// check to see if our "propPath" actually
			// represents a property on the persister
			propertyType = fromElement.getPropertyType(getText(), propertyPath);
		}
		catch (Throwable t) {
			// assume we do *not* refer to a property on the given persister
			return false;
		}

		setFromElement(fromElement);
		// this piece is needed for usage in select clause
		super.setDataType(propertyType);
		nakedPropertyRef = true;

		return true;
	}
