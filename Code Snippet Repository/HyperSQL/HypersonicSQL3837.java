    protected static void errorMessage(String errorMessage) {

/**/
/**/
/**/
/**/
        Object[] options = { "OK" };

        JOptionPane.showOptionDialog(null, errorMessage, messagerHeader,
                                     JOptionPane.DEFAULT_OPTION,
                                     JOptionPane.WARNING_MESSAGE, null,
                                     options, options[0]);

        // DatabaseManagerSwing.StatusMessage(READY_STATUS);
    }
