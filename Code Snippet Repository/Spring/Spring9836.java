		@Override
		public Mono<Void> changeSessionId() {
			String currentId = this.id.get();
			if (InMemoryWebSessionStore.this.sessions.remove(currentId) == null) {
				return Mono.error(new IllegalStateException(
						"Failed to change session id: " + currentId +
								" because the Session is no longer present in the store."));
			}
			String newId = String.valueOf(idGenerator.generateId());
			this.id.set(newId);
			InMemoryWebSessionStore.this.sessions.put(this.getId(), this);
			return Mono.empty();
		}
