	public static SpelCompiler getCompiler(@Nullable ClassLoader classLoader) {
		ClassLoader clToUse = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
		synchronized (compilers) {
			SpelCompiler compiler = compilers.get(clToUse);
			if (compiler == null) {
				compiler = new SpelCompiler(clToUse);
				compilers.put(clToUse, compiler);
			}
			return compiler;
		}
	}
