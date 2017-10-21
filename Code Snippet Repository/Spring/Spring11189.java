	private List<ContentChunkInfo> parseContent(String cssContent) {
		SortedSet<ContentChunkInfo> links = new TreeSet<>();
		this.linkParsers.forEach(parser -> parser.parse(cssContent, links));
		if (links.isEmpty()) {
			return Collections.emptyList();
		}
		int index = 0;
		List<ContentChunkInfo> result = new ArrayList<>();
		for (ContentChunkInfo link : links) {
			result.add(new ContentChunkInfo(index, link.getStart(), false));
			result.add(link);
			index = link.getEnd();
		}
		if (index < cssContent.length()) {
			result.add(new ContentChunkInfo(index, cssContent.length(), false));
		}
		return result;
	}
