    public static Menu getMenu(String menuId, String currentAction, User user,
            Weblog weblog) {

        if (menuId == null) {
            return null;
        }

        Menu menu = null;

        // do we know the specified menu config?
        ParsedMenu menuConfig = menus.get(menuId);
        if (menuConfig != null) {
            try {
                menu = buildMenu(menuId, menuConfig, currentAction, user,
                        weblog);
            } catch (WebloggerException ex) {
                log.error("ERROR: fethcing user roles", ex);
            }
        }

        return menu;
    }
