    private void createWidgets() {
        configTextArea = new JTextArea(CONFIG_TEXT_ROWS, CONFIG_TEXT_COLS);
        // configTextArea.setEditable(false);
        configTextArea.setBackground(Color.white);
        configTextArea.setForeground(Color.black);
        configTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, configTextArea.getFont().getSize()));
        final JScrollPane scrollConfig = new JScrollPane(configTextArea);

        locationTextField = new JTextField(LOCATION_TEXT_COLS);
        locationLabel = new JLabel("Location: ");
        locationLabel.setLabelFor(locationTextField);
        buttonSendLocation = new JButton(actionReconfigureFromLocation);
        buttonSendConfigText = new JButton(actionReconfigureFromText);

        final JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.LINE_AXIS));
        north.add(locationLabel);
        north.add(locationTextField);
        north.add(buttonSendLocation);
        north.add(Box.createRigidArea(new Dimension(HORIZONTAL_GAP, 0)));
        north.add(buttonSendConfigText);

        this.setLayout(new BorderLayout());
        this.add(north, BorderLayout.NORTH);
        this.add(scrollConfig, BorderLayout.CENTER);
    }
