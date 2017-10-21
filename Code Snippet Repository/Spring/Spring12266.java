		@Override
		public void parse(String content, SortedSet<ContentChunkInfo> result) {
			int position = 0;
			while (true) {
				position = content.indexOf(getKeyword(), position);
				if (position == -1) {
					return;
				}
				position += getKeyword().length();
				while (Character.isWhitespace(content.charAt(position))) {
					position++;
				}
				if (content.charAt(position) == '\'') {
					position = extractLink(position, "'", content, result);
				}
				else if (content.charAt(position) == '"') {
					position = extractLink(position, "\"", content, result);
				}
				else {
					position = extractLink(position, content, result);

				}
			}
		}
