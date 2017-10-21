		@Nullable
		public static <T> Constructor<T> findPrimaryConstructor(Class<T> clazz) {
			try {
				KFunction<T> primaryCtor = KClasses.getPrimaryConstructor(JvmClassMappingKt.getKotlinClass(clazz));
				if (primaryCtor == null) {
					return null;
				}
				Constructor<T> constructor = ReflectJvmMapping.getJavaConstructor(primaryCtor);
				Assert.notNull(constructor,
						() -> "Failed to find Java constructor for Kotlin primary constructor: " + clazz.getName());
				return constructor;
			}
			catch (UnsupportedOperationException ex) {
				return null;
			}
		}
