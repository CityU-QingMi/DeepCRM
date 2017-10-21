    public Definition buildTilesDefinition(String tileName, TilesDefinition tilesDefinition) {
        Definition definition = new Definition();

        definition.setName(tileName);

        String extend = getValueOrNull(tilesDefinition.extend());
        if (extend != null) {
            definition.setExtends(extend);
        }
        String preparer = getValueOrNull(tilesDefinition.preparer());
        if (preparer != null) {
            definition.setPreparer(preparer);
        }
        definition.setTemplateAttribute(buildTemplateAttribute(tilesDefinition));

        for (TilesPutAttribute putAttribute : tilesDefinition.putAttributes()) {
            Attribute attribute = buildPutAttribute(putAttribute);
            definition.putAttribute(putAttribute.name(), attribute, putAttribute.cascade());
        }
        for (TilesPutListAttribute putListAttribute : tilesDefinition.putListAttributes()) {
            Attribute attribute = buildPutListAttribute(putListAttribute);
            definition.putAttribute(putListAttribute.name(), attribute, putListAttribute.cascade());
        }

        return definition;
    }
