	private String pathToString(int fromSegment, List<Element> pathElements) {
		StringBuilder buf = new StringBuilder();
		for (int i = fromSegment, max = pathElements.size(); i < max; i++) {
			Element element = pathElements.get(i);
			if (element instanceof PathSegment) {
				buf.append(((PathSegment)element).valueToMatch());
			}
			else {
				buf.append(element.value());
			}
		}
		return buf.toString();
	}
