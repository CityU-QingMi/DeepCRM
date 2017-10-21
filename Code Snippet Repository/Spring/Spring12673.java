		@SuppressWarnings("")
		@Override
		public MessageCodesResolver getMessageCodesResolver() {
			return new DefaultMessageCodesResolver() {
				@Override
				public String[] resolveMessageCodes(String errorCode, String objectName) {
					return new String[] { "custom." + errorCode };
				}
			};
		}
