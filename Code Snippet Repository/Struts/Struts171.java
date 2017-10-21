    private void verifyPackageStructure() {
        DirectedGraph<String> graph = new DirectedGraph<>();

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
                        String packageName = child.getAttribute("name");
                        declaredPackages.put(packageName, child);
                        graph.addNode(packageName);

                        String extendsAttribute = child.getAttribute("extends");
                        List<String> parents = ConfigurationUtil.buildParentListFromString(extendsAttribute);
                        for (String parent : parents) {
                            graph.addNode(parent);
                            graph.addEdge(packageName, parent);
                        }
                    }
                }
            }
        }

        CycleDetector<String> detector = new CycleDetector<>(graph);
        if (detector.containsCycle()) {
            StringBuilder builder = new StringBuilder("The following packages participate in cycles:");
            for (String packageName : detector.getVerticesInCycles()) {
                builder.append(" ");
                builder.append(packageName);
            }
            throw new ConfigurationException(builder.toString());
        }
    }
