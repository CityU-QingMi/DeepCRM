			@Override
			public Object getPayload() {
				if (this.payload == null) {
					try {
						this.payload = unwrapPayload();
					}
					catch (JMSException ex) {
						throw new MessageConversionException(
								"Failed to extract payload from [" + this.message + "]", ex);
					}
				}
				return this.payload;
			}
