	public Vector<String> findMatches() {
		Vector<String> matches = new Vector<>();
		URLClassLoader cl = getURLClassLoader();
		if (cl == null ) {
			throw new XWorkException("unable to attain an URLClassLoader") ;
		}
		URL[] parentUrls = cl.getURLs();
		compiledPattern = patternMatcher.compilePattern(pattern);
		for (URL url : parentUrls) {
			if (!"file".equals(url.getProtocol())) {
				continue ;
			}
			URI entryURI ;
			try {
				entryURI = url.toURI();
			} catch (URISyntaxException e) {
				continue;
			}
			File entry = new File(entryURI);
			if (entry.isFile() && entry.toString().endsWith(".jar")) {
				try {
					ZipInputStream zip = new ZipInputStream(new FileInputStream(entry));
					for (ZipEntry zipEntry = zip.getNextEntry(); zipEntry != null; zipEntry = zip.getNextEntry()) {
						boolean doesMatch = patternMatcher.match(new HashMap<String, String>(), zipEntry.getName(), compiledPattern);
						if (doesMatch) {
							matches.add(zipEntry.getName());
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				Vector<String> results = checkEntries(entry.list(), entry, "");
				if (results != null) {
					matches.addAll(results);
				}
			}
		}
		return matches;
	}
