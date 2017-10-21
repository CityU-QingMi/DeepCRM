    protected Map<String, List<Action>> getActionAnnotations(Class<?> actionClass) {
        Method[] methods = actionClass.getMethods();
        Map<String, List<Action>> map = new HashMap<>();
        for (Method method : methods) {
            Actions actionsAnnotation = method.getAnnotation(Actions.class);
            if (actionsAnnotation != null) {
                List<Action> actions = checkActionsAnnotation(actionsAnnotation);
                map.put(method.getName(), actions);
            } else {
                Action ann = method.getAnnotation(Action.class);
                if (ann != null) {
                    map.put(method.getName(), Arrays.asList(ann));
                }
            }
        }

        return map;
    }
