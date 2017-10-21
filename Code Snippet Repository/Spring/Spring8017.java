		@Override
		public DataHandler getAttachmentAsDataHandler(String contentId) {
			if (contentId.startsWith(CID)) {
				contentId = contentId.substring(CID.length());
				try {
					contentId = URLDecoder.decode(contentId, "UTF-8");
				}
				catch (UnsupportedEncodingException ex) {
					// ignore
				}
				contentId = '<' + contentId + '>';
			}
			DataHandler dataHandler = this.mimeContainer.getAttachment(contentId);
			if (dataHandler == null) {
				throw new IllegalArgumentException("No attachment found for " + contentId);
			}
			return dataHandler;
		}
