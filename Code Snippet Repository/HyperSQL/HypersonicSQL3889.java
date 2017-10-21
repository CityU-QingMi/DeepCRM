    private JMenu addMenu(JMenuBar b, String name, Object[] items) {

        JMenu menu = new JMenu(name);

        menu.setMnemonic(name.charAt(0));
        addMenuItems(menu, items);
        b.add(menu);

        return menu;
    }
