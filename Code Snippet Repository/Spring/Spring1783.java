	private Element getCandidateAnnotationElement(Set<Element> seen, AnnotationMirror annotation) {
		Element element = annotation.getAnnotationType().asElement();
		if (seen.contains(element)) {
			return null;
		}
		// We need to visit all indexed annotations.
		if (!isIndexedAnnotation(annotation)) {
			seen.add(element);
		}
		return (!element.toString().startsWith("java.lang") ? element : null);
	}
