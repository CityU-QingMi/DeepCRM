		@Override
		protected int extractUnquotedLink(int position, String content, Set<ContentChunkInfo> result) {
			if (content.substring(position, position + 4).equals("url(")) {
				// Ignore, UrlFunctionContentParser will take care
			}
			else if (logger.isErrorEnabled()) {
				logger.error("Unexpected syntax for @import link at index " + position);
			}
			return position;
		}
