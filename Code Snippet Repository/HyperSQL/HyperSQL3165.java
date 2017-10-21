    public static Connection createConnection(JFrame owner, String title) {

        ConnectionDialogSwing dialog = new ConnectionDialogSwing(owner,
            title);

//      Added: (weconsultants@users) Default LAF of Native
        try {

//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(dialog);
        } catch (Exception e) {
            CommonSwing.errorMessage(e);
        }

        dialog.create();

        return dialog.mConnection;
    }
