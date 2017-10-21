    public TilesDefinition findAnnotation(Object action, String tileName) {
        Class<?> clazz = action.getClass();
        TilesDefinition tilesDefinition = clazz.getAnnotation(TilesDefinition.class);
        TilesDefinitions tilesDefinitions = clazz.getAnnotation(TilesDefinitions.class);

        if (tilesDefinition == null && tilesDefinitions != null) {
            if (!StringUtils.isEmpty(tileName)) {
                for (TilesDefinition i : tilesDefinitions.value()) {
                    if (i.name().equals(tileName)) {
                        tilesDefinition = i;
                        break;
                    }
                }
            } else {
                if (tilesDefinitions.value().length > 0) {
                    tilesDefinition = tilesDefinitions.value()[0];
                }
            }
        }

        return tilesDefinition;
    }
