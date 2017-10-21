    private void addMenuItems(Menu f, String[] m) {

        for (int i = 0; i < m.length; i++) {
            if (m[i].equals("-")) {
                f.addSeparator();
            } else {
                MenuItem item = new MenuItem(m[i]);

                item.addActionListener(this);
                f.add(item);
            }
        }
    }
