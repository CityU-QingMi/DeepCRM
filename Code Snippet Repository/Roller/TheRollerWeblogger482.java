    private static boolean isSelected(String currentAction,
            ParsedTabItem tabItem) {

        if (currentAction.equals(tabItem.getAction())) {
            return true;
        }

        // an item is also considered selected if it's a subforward of the
        // current action
        Set<String> subActions = tabItem.getSubActions();

        return subActions != null && subActions.contains(currentAction);
    }
