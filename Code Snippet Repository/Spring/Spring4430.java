		public List<String> getAttributeAliasNames() {
			// Explicit alias pair?
			if (this.isAliasPair) {
				return Collections.singletonList(this.aliasedAttributeName);
			}

			// Else: search for implicit aliases
			List<String> aliases = new ArrayList<>();
			for (AliasDescriptor otherDescriptor : getOtherDescriptors()) {
				if (this.isAliasFor(otherDescriptor)) {
					this.validateAgainst(otherDescriptor);
					aliases.add(otherDescriptor.sourceAttributeName);
				}
			}
			return aliases;
		}
