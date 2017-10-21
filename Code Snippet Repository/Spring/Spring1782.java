	private void collectStereotypesOnTypes(Set<Element> seen, Set<String> stereotypes, Element type) {
		if (!seen.contains(type)) {
			seen.add(type);
			if (isAnnotatedWithIndexed(type)) {
				stereotypes.add(this.typeHelper.getType(type));
			}
			Element superClass = this.typeHelper.getSuperClass(type);
			if (superClass != null) {
				collectStereotypesOnTypes(seen, stereotypes, superClass);
			}
			this.typeHelper.getDirectInterfaces(type).forEach(
					i -> collectStereotypesOnTypes(seen, stereotypes, i));
		}
	}
