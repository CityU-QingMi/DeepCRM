		public void setImmutable() {
			if (!this.mutable) {
				return;
			}

			if (getId() == null) {
				IdGenerator idGenerator = (MessageHeaderAccessor.this.idGenerator != null ?
						MessageHeaderAccessor.this.idGenerator : MessageHeaders.getIdGenerator());
				UUID id = idGenerator.generateId();
				if (id != MessageHeaders.ID_VALUE_NONE) {
					getRawHeaders().put(ID, id);
				}
			}

			if (getTimestamp() == null) {
				if (MessageHeaderAccessor.this.enableTimestamp) {
					getRawHeaders().put(TIMESTAMP, System.currentTimeMillis());
				}
			}

			this.mutable = false;
		}
