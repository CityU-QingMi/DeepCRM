    void addMenuItems(Menu f, String[] m) {

        for (int i = 0; i < m.length; i++) {
            MenuItem item = new MenuItem(m[i].substring(1));
            char     c    = m[i].charAt(0);

            if (c != '-') {
                item.setShortcut(new MenuShortcut(c));
            }

            item.addActionListener(this);
            f.add(item);
        }
    }
