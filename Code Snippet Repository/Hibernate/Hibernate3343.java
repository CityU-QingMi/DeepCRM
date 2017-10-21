	public XMLHelper(ClassLoaderService classLoaderService) {
		this.documentFactory = classLoaderService.workWithClassLoader(
				new ClassLoaderService.Work<DocumentFactory>() {
					@Override
					public DocumentFactory doWork(ClassLoader classLoader) {
						final ClassLoader originalTccl = Thread.currentThread().getContextClassLoader();
						try {
							Thread.currentThread().setContextClassLoader( classLoader );
							return DocumentFactory.getInstance();
						}
						finally {
							Thread.currentThread().setContextClassLoader( originalTccl );
						}
					}
				}
		);

	}
