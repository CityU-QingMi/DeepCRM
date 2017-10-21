    void addMenu(MenuBar b, String name, String[] items) {

/**/
/**/
/**/
        Menu menu = new Menu(name);

        if (name.equals("Tools") && !TT_AVAILABLE) {

            // Terrible place to do this.  Forced to due to method design.
            menu.setEnabled(false);
        }

        addMenuItems(menu, items);
        b.add(menu);
    }
