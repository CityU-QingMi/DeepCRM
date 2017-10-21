	protected List<SpringPersistenceUnitInfo> parseDocument(
			Resource resource, Document document, List<SpringPersistenceUnitInfo> infos) throws IOException {

		Element persistence = document.getDocumentElement();
		String version = persistence.getAttribute(PERSISTENCE_VERSION);
		URL rootUrl = determinePersistenceUnitRootUrl(resource);

		List<Element> units = DomUtils.getChildElementsByTagName(persistence, PERSISTENCE_UNIT);
		for (Element unit : units) {
			infos.add(parsePersistenceUnitInfo(unit, version, rootUrl));
		}

		return infos;
	}
