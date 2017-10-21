		@Override
		public boolean matches(Object value) {
			if (!(value instanceof CandidateComponentsMetadata)) {
				return false;
			}
			ItemMetadata itemMetadata = getFirstItemWithType((CandidateComponentsMetadata) value, this.type);
			if (itemMetadata == null) {
				return false;
			}
			if (this.type != null && !this.type.equals(itemMetadata.getType())) {
				return false;
			}
			if (this.stereotypes != null) {
				for (String stereotype : this.stereotypes) {
					if (!itemMetadata.getStereotypes().contains(stereotype)) {
						return false;
					}
				}
				if (this.stereotypes.size() != itemMetadata.getStereotypes().size()) {
					return false;
				}
			}
			return true;
		}
