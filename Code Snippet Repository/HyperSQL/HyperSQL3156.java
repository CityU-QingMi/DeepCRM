    static void setFramePositon(JFrame inTargetFrame) {

        Dimension d    = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension size = inTargetFrame.getSize();

        // (ulrivo): full size on screen with less than 640 width
        if (d.width >= 640) {
            inTargetFrame.setLocation((d.width - size.width) / 2,
                                      (d.height - size.height) / 2);
        } else {
            inTargetFrame.setLocation(0, 0);
            inTargetFrame.setSize(d);
        }
    }
