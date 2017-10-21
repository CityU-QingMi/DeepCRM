		private boolean readNextEntity() {
			try {
				while (navigateToNextEntity()) {
					String entityName = nextWordToken();
					if ("CDATA".equals(nextWordToken())) {
						int referredCharacter = nextReferredCharacterId();
						if (entityName != null && referredCharacter != -1) {
							this.currentEntityName = entityName;
							this.referredCharacter = referredCharacter;
							return true;
						}
					}
				}
				return false;
			}
			catch (IOException ex) {
				throw new IllegalStateException("Could not parse defintion resource: " + ex.getMessage());
			}
		}
