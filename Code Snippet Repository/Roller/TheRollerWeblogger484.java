    private static ParsedTabItem elementToParsedTabItem(Element element) {

        ParsedTabItem tabItem = new ParsedTabItem();

        tabItem.setName(element.getAttributeValue("name"));
        tabItem.setAction(element.getAttributeValue("action"));

        String subActions = element.getAttributeValue("subactions");
        if (subActions != null) {
            Set<String> set = new HashSet<String>();
            for (String string : Utilities.stringToStringList(subActions, ",")) {
                set.add(string);
            }
            tabItem.setSubActions(set);
        }

        if (element.getAttributeValue("weblogPerms") != null) {
            tabItem.setWeblogPermissionActions(Utilities.stringToStringList(
                    element.getAttributeValue("weblogPerms"), ","));
        }
        if (element.getAttributeValue("globalPerms") != null) {
            tabItem.setGlobalPermissionActions(Utilities.stringToStringList(
                    element.getAttributeValue("globalPerms"), ","));
        }
        tabItem.setEnabledProperty(element.getAttributeValue("enabledProperty"));
        tabItem.setDisabledProperty(element
                .getAttributeValue("disabledProperty"));

        return tabItem;
    }
