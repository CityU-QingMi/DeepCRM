		@Override
		protected Class<?> loadClassForOverriding(String name) throws ClassNotFoundException {
			byte[] bytes = bytesCache.get(name);
			if (bytes == null) {
				bytes = loadBytesForClass(name);
				if (bytes != null) {
					bytesCache.put(name, bytes);
				}
				else {
					return null;
				}
			}
			return defineClass(name, bytes, 0, bytes.length);
		}
