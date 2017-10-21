    private JScrollPane scroll(final JTextArea text) {
        final JToggleButton toggleButton = new JToggleButton();
        toggleButton.setAction(new AbstractAction() {
            private static final long serialVersionUID = -4214143754637722322L;

            @Override
            public void actionPerformed(final ActionEvent e) {
                final boolean wrap = toggleButton.isSelected();
                text.setLineWrap(wrap);
            }
        });
        toggleButton.setToolTipText("Toggle line wrapping");
        final JScrollPane scrollStatusLog = new JScrollPane(text, //
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, //
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollStatusLog.setCorner(ScrollPaneConstants.LOWER_RIGHT_CORNER, toggleButton);
        return scrollStatusLog;
    }
