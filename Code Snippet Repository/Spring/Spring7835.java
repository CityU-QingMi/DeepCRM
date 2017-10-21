	public SpringPersistenceUnitInfo[] readPersistenceUnitInfos(String[] persistenceXmlLocations) {
		ErrorHandler handler = new SimpleSaxErrorHandler(logger);
		List<SpringPersistenceUnitInfo> infos = new LinkedList<>();
		String resourceLocation = null;
		try {
			for (String location : persistenceXmlLocations) {
				Resource[] resources = this.resourcePatternResolver.getResources(location);
				for (Resource resource : resources) {
					resourceLocation = resource.toString();
					InputStream stream = resource.getInputStream();
					try {
						Document document = buildDocument(handler, stream);
						parseDocument(resource, document, infos);
					}
					finally {
						stream.close();
					}
				}
			}
		}
		catch (IOException ex) {
			throw new IllegalArgumentException("Cannot parse persistence unit from " + resourceLocation, ex);
		}
		catch (SAXException ex) {
			throw new IllegalArgumentException("Invalid XML in persistence unit from " + resourceLocation, ex);
		}
		catch (ParserConfigurationException ex) {
			throw new IllegalArgumentException("Internal error parsing persistence unit from " + resourceLocation);
		}

		return infos.toArray(new SpringPersistenceUnitInfo[infos.size()]);
	}
