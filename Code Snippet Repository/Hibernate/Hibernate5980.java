	@Test
	@TestForIssue(jiraKey = "")
	public void testJarVisitorFactory() throws Exception {
		final File explodedPar = buildExplodedPar();
		final File defaultPar = buildDefaultPar();
		addPackageToClasspath( explodedPar, defaultPar );

		//setting URL to accept vfs based protocol
		URL.setURLStreamHandlerFactory(new URLStreamHandlerFactory() {
										   public URLStreamHandler createURLStreamHandler(String protocol) {
											   if("vfszip".equals(protocol) || "vfsfile".equals(protocol) )
												   return new URLStreamHandler() {
													   protected URLConnection openConnection(URL u)
															   throws IOException {
														   return null;
													   }
												   };
											   return null;
										   }
									   });

		URL jarUrl = defaultPar.toURL();
		ArchiveDescriptor descriptor = StandardArchiveDescriptorFactory.INSTANCE.buildArchiveDescriptor( jarUrl );
		assertEquals( JarFileBasedArchiveDescriptor.class.getName(), descriptor.getClass().getName() );

		jarUrl  = explodedPar.toURL();
		descriptor = StandardArchiveDescriptorFactory.INSTANCE.buildArchiveDescriptor( jarUrl );
		assertEquals( ExplodedArchiveDescriptor.class.getName(), descriptor.getClass().getName() );

		jarUrl  = new URL( defaultPar.toURL().toExternalForm().replace( "file:", "vfszip:" ) );
		descriptor = StandardArchiveDescriptorFactory.INSTANCE.buildArchiveDescriptor( jarUrl );
		assertEquals( JarFileBasedArchiveDescriptor.class.getName(), descriptor.getClass().getName());

		jarUrl  = new URL( explodedPar.toURL().toExternalForm().replace( "file:", "vfsfile:" ) );
		descriptor = StandardArchiveDescriptorFactory.INSTANCE.buildArchiveDescriptor( jarUrl );
		assertEquals( ExplodedArchiveDescriptor.class.getName(), descriptor.getClass().getName() );
	}
