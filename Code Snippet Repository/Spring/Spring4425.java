		@Nullable
		public static AliasDescriptor from(Method attribute) {
			AliasDescriptor descriptor = aliasDescriptorCache.get(attribute);
			if (descriptor != null) {
				return descriptor;
			}

			AliasFor aliasFor = attribute.getAnnotation(AliasFor.class);
			if (aliasFor == null) {
				return null;
			}

			descriptor = new AliasDescriptor(attribute, aliasFor);
			descriptor.validate();
			aliasDescriptorCache.put(attribute, descriptor);
			return descriptor;
		}
