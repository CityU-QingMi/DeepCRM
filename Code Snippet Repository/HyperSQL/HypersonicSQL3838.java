    public static void errorMessage(Exception exceptionMsg, boolean quiet) {

/**/
/**/
/**/
/**/
        Object[] options = { "OK", };

        JOptionPane.showOptionDialog(null, exceptionMsg, messagerHeader,
                                     JOptionPane.DEFAULT_OPTION,
                                     JOptionPane.ERROR_MESSAGE, null,
                                     options, options[0]);

        if (!quiet) {
            exceptionMsg.printStackTrace();
        }

        // DatabaseManagerSwing.StatusMessage(READY_STATUS);
    }
