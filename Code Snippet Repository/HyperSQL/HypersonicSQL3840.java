    static void setSwingLAF(java.awt.Component comp, String targetTheme) {

        try {
            if (targetTheme.equalsIgnoreCase(Native)) {
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
            } else if (targetTheme.equalsIgnoreCase(Java)) {
                UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
            } else if (targetTheme.equalsIgnoreCase(Motif)) {
                UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            }

//            if (targetTheme.equalsIgnoreCase(plaf)){
//                UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//            }
//
//            if (targetTheme.equalsIgnoreCase(GTK)){
//                UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
//            }
            SwingUtilities.updateComponentTreeUI(comp);

            if (comp instanceof java.awt.Frame) {
                ((java.awt.Frame) comp).pack();
            }
        } catch (Exception e) {
            errorMessage(e);
        }
    }
