	static PathContainer createFromUrlPath(String path) {
		if (path.equals("")) {
			return EMPTY_PATH;
		}
		String separator = "/";
		Separator separatorElement = separator.equals(SEPARATOR.value()) ? SEPARATOR : () -> separator;
		List<Element> elements = new ArrayList<>();
		int begin;
		if (path.length() > 0 && path.startsWith(separator)) {
			begin = separator.length();
			elements.add(separatorElement);
		}
		else {
			begin = 0;
		}
		while (begin < path.length()) {
			int end = path.indexOf(separator, begin);
			String segment = (end != -1 ? path.substring(begin, end) : path.substring(begin));
			if (!segment.equals("")) {
				elements.add(parsePathSegment(segment));
			}
			if (end == -1) {
				break;
			}
			elements.add(separatorElement);
			begin = end + separator.length();
		}
		return new DefaultPathContainer(path, elements);
	}
