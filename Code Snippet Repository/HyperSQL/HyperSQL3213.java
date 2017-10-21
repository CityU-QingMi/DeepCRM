    private void addMenuItems(JMenu f, Object[] m) {

/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        for (int i = 0; i < m.length; i++) {
            if (m[i].equals("--")) {
                f.addSeparator();
            } else if (m[i].equals("---")) {

                // (ulrivo): full size on screen with less than 640 width
                if (d.width >= 640) {
                    f.addSeparator();
                } else {
                    return;
                }
            } else {
                JMenuItem item;

                if (m[i] instanceof JMenuItem) {
                    item = (JMenuItem) m[i];
                } else if (m[i] instanceof String) {
                    item = new JMenuItem(((String) m[i]).substring(1));

                    char c = ((String) m[i]).charAt(0);

                    if (c != '-') {
                        KeyStroke key =
                            KeyStroke.getKeyStroke(c, Event.CTRL_MASK);

                        item.setAccelerator(key);
                    }
                } else {
                    throw new RuntimeException(
                        "Unexpected element for menu item creation: "
                        + m[i].getClass().getName());
                }

                item.addActionListener(this);
                f.add(item);
            }
        }
    }
