    static Image getIcon(String target) {

        if (target.equalsIgnoreCase("SystemCursor")) {
            return (new ImageIcon(
                CommonSwing.class.getResource("Hourglass.gif")).getImage());
        } else if (target.equalsIgnoreCase("Frame")) {
            return (new ImageIcon(
                CommonSwing.class.getResource("hsqldb.gif")).getImage());
        } else if (target.equalsIgnoreCase("Execute")) {
            return (new ImageIcon(
                CommonSwing.class.getResource("run_exc.gif")).getImage());
        } else if (target.equalsIgnoreCase("StatusRunning")) {
            return (new ImageIcon(
                CommonSwing.class.getResource("RedCircle.gif")).getImage());
        } else if (target.equalsIgnoreCase("StatusReady")) {
            return (new ImageIcon(
                CommonSwing.class.getResource("GreenCircle.gif")).getImage());
        } else if (target.equalsIgnoreCase("Clear")) {
            return (new ImageIcon(
                CommonSwing.class.getResource("Clear.png")).getImage());
        } else if (target.equalsIgnoreCase("Problem")) {
            return (new ImageIcon(
                CommonSwing.class.getResource("problems.gif")).getImage());
        } else if (target.equalsIgnoreCase("BoldFont")) {
            return (new ImageIcon(
                CommonSwing.class.getResource("Bold.gif")).getImage());
        } else if (target.equalsIgnoreCase("ItalicFont")) {
            return (new ImageIcon(
                CommonSwing.class.getResource("Italic.gif")).getImage());
        } else if (target.equalsIgnoreCase("ColorSelection")) {
            return (new ImageIcon(
                CommonSwing.class.getResource("Colors.png")).getImage());
        } else if (target.equalsIgnoreCase("Close")) {
            return (new ImageIcon(
                CommonSwing.class.getResource("Close.png")).getImage());
        } else {
            return (null);
        }
    }
