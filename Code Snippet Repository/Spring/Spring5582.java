		@Override
		public boolean equals(Object obj) {
			Attributes other = ((PartialAttributes) obj).attributes;
			if (this.attributes.getLength() != other.getLength()) {
				return false;
			}
			for (int i = 0; i < other.getLength(); i++) {
				boolean found = false;
				for (int j = 0; j < attributes.getLength(); j++) {
					if (other.getURI(i).equals(attributes.getURI(j))
							&& other.getQName(i).equals(attributes.getQName(j))
							&& other.getType(i).equals(attributes.getType(j))
							&& other.getValue(i).equals(attributes.getValue(j))) {
						found = true;
						break;
					}
				}
				if (!found) {
					return false;
				}
			}
			return true;
		}
