    @Override
    public void setup() {
        final List<Node> children = rootNode.getChildren();
        if (propertiesComponent.getComponents().size() > 0) {
            children.add(convertToNode(rootNode, propertiesComponent));
        }
        if (scriptsComponent.getComponents().size() > 0) {
            children.add(convertToNode(rootNode, scriptsComponent));
        }
        if (customLevelsComponent.getComponents().size() > 0) {
            children.add(convertToNode(rootNode, customLevelsComponent));
        }
        children.add(convertToNode(rootNode, loggersComponent));
        children.add(convertToNode(rootNode, appendersComponent));
        if (filtersComponent.getComponents().size() > 0) {
            if (filtersComponent.getComponents().size() == 1) {
                children.add(convertToNode(rootNode, filtersComponent.getComponents().get(0)));
            } else {
                children.add(convertToNode(rootNode, filtersComponent));
            }
        }
        rootComponent = null;
    }
