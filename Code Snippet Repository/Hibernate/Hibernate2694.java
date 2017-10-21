	private boolean resolveAsNakedComponentPropertyRefLHS(DotNode parent) {
		FromElement fromElement = locateSingleFromElement();
		if (fromElement == null) {
			return false;
		}

		Type componentType = getNakedPropertyType(fromElement);
		if ( componentType == null ) {
			throw new QueryException( "Unable to resolve path [" + parent.getPath() + "], unexpected token [" + getOriginalText() + "]" );
		}
		if (!componentType.isComponentType()) {
			throw new QueryException("Property '" + getOriginalText() + "' is not a component.  Use an alias to reference associations or collections.");
		}

		Type propertyType;
		String propertyPath = getText() + "." + getNextSibling().getText();
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
		parent.setPropertyPath(propertyPath);
		parent.setDataType(propertyType);

		return true;
	}
