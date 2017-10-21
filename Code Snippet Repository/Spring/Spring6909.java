		public boolean containsComponentDefinition(String name) {
			for (ComponentDefinition cd : this.registeredComponents) {
				if (cd instanceof CompositeComponentDefinition) {
					ComponentDefinition[] innerCds = ((CompositeComponentDefinition) cd).getNestedComponents();
					for (ComponentDefinition innerCd : innerCds) {
						if (innerCd.getName().equals(name)) {
							return true;
						}
					}
				}
				else {
					if (cd.getName().equals(name)) {
						return true;
					}
				}
			}
			return false;
		}
