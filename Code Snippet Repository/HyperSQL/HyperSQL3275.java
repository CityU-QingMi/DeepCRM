    private Panel createBorderPanel(Component center) {

        Panel p = new Panel();

        p.setBackground(SystemColor.control);
        p.setLayout(new BorderLayout());
        p.add("Center", center);
        p.add("South", createLabel(""));
        p.add("East", createLabel(""));
        p.add("West", createLabel(""));
        p.setBackground(SystemColor.control);

        return p;
    }
