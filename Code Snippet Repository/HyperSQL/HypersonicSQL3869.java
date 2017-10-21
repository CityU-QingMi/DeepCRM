        public void run() {

            boolean havesql;

            while (true) {
                try {
                    Thread.sleep(BUTTON_CHECK_PERIOD);
                } catch (InterruptedException ie) {}

                if (buttonUpdaterThread == null) {    // Pointer to me
                    return;
                }

                havesql = (txtCommand.getText().length() > 0);

                if (jbuttonClear.isEnabled() != havesql) {
                    SwingUtilities.invokeLater(havesql ? enableButtonRunnable
                                                       : disableButtonRunnable);
                }
            }
        }
