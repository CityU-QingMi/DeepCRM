	@Override
	public Set<String> getStereotypes(Element element) {
		Set<String> stereotypes = new LinkedHashSet<>();
		ElementKind kind = element.getKind();
		if (kind != ElementKind.CLASS && kind != ElementKind.INTERFACE) {
			return stereotypes;
		}
		Set<Element> seen = new HashSet<>();
		collectStereotypesOnAnnotations(seen, stereotypes, element);
		seen = new HashSet<>();
		collectStereotypesOnTypes(seen, stereotypes, element);
		return stereotypes;
	}
