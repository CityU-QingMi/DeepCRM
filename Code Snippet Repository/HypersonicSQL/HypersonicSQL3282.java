    protected void initComponents() {

        Container main = getContentPane();

        textArea = new JTextArea();

        JPanel  buttons = new JPanel(new FlowLayout());
        JButton close   = new JButton("Close Gracefully");

        close.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                doClose();
            }
        });

        JButton create = new JButton("Add Row");

        create.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                doInsert();
            }
        });

        JButton list = new JButton("List Data");

        list.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                doListing();
            }
        });

        JButton kill = new JButton("Kill");

        kill.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttons.add(create);
        buttons.add(list);
        buttons.add(kill);
        buttons.add(close);
        main.add(new JScrollPane(textArea), BorderLayout.CENTER);
        main.add(buttons, BorderLayout.SOUTH);
    }
