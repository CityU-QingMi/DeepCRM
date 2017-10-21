    @Override
    public void mergeRootProperties(final Node rootNode, final AbstractConfiguration configuration) {
        for (final Map.Entry<String, String> attribute : configuration.getRootNode().getAttributes().entrySet()) {
            boolean isFound = false;
            for (final Map.Entry<String, String> targetAttribute : rootNode.getAttributes().entrySet()) {
                if (targetAttribute.getKey().equalsIgnoreCase(attribute.getKey())) {
                    if (attribute.getKey().equalsIgnoreCase(STATUS)) {
                        final Level targetLevel = Level.getLevel(targetAttribute.getValue().toUpperCase());
                        final Level sourceLevel = Level.getLevel(attribute.getValue().toUpperCase());
                        if (targetLevel != null && sourceLevel != null) {
                            if (sourceLevel.isLessSpecificThan(targetLevel)) {
                                targetAttribute.setValue(attribute.getValue());
                            }
                        } else
                            if (sourceLevel != null) {
                                targetAttribute.setValue(attribute.getValue());
                            }
                    } else {
                        if (attribute.getKey().equalsIgnoreCase("monitorInterval")) {
                            final int sourceInterval = Integer.parseInt(attribute.getValue());
                            final int targetInterval = Integer.parseInt(targetAttribute.getValue());
                            if (targetInterval == 0 || sourceInterval < targetInterval) {
                                targetAttribute.setValue(attribute.getValue());
                            }
                        } else {
                            targetAttribute.setValue(attribute.getValue());
                        }
                    }
                    isFound = true;
                }
            }
            if (!isFound) {
                rootNode.getAttributes().put(attribute.getKey(), attribute.getValue());
            }
        }
    }
