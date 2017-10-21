	@Override
	public Set<String> getStereotypes(Element element) {
		Set<String> stereotypes = new LinkedHashSet<>();
		ElementKind kind = element.getKind();
		if (kind != ElementKind.CLASS && kind != ElementKind.INTERFACE) {
			return stereotypes;
		}
		for (AnnotationMirror annotation : this.typeHelper.getAllAnnotationMirrors(element)) {
			String type = this.typeHelper.getType(annotation);
			if (type.startsWith("javax.")) {
				stereotypes.add(type);
			}
		}
		return stereotypes;
	}
