    protected List<Action> checkActionsAnnotation(Actions actionsAnnotation) {
        Action[] actionArray = actionsAnnotation.value();
        boolean valuelessSeen = false;
        List<Action> actions = new ArrayList<>();
        for (Action ann : actionArray) {
            if (ann.value().equals(Action.DEFAULT_VALUE) && !valuelessSeen) {
                valuelessSeen = true;
            } else if (ann.value().equals(Action.DEFAULT_VALUE)) {
                throw new ConfigurationException("You may only add a single Action annotation that has no value parameter.");
            }

            actions.add(ann);
        }
        return actions;
    }
