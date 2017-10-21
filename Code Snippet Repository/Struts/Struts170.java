    public void loadPackages() throws ConfigurationException {
        List<Element> reloads = new ArrayList<Element>();
        verifyPackageStructure();

        for (Document doc : documents) {
            Element rootElement = doc.getDocumentElement();
            NodeList children = rootElement.getChildNodes();
            int childSize = children.getLength();

            for (int i = 0; i < childSize; i++) {
                Node childNode = children.item(i);

                if (childNode instanceof Element) {
                    Element child = (Element) childNode;

                    final String nodeName = child.getNodeName();

                    if ("package".equals(nodeName)) {
                        PackageConfig cfg = addPackage(child);
                        if (cfg.isNeedsRefresh()) {
                            reloads.add(child);
                        }
                    }
                }
            }
            loadExtraConfiguration(doc);
        }

        if (reloads.size() > 0) {
            reloadRequiredPackages(reloads);
        }

        for (Document doc : documents) {
            loadExtraConfiguration(doc);
        }

        documents.clear();
        declaredPackages.clear();
        configuration = null;
    }
