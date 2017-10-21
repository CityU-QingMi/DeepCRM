    private void backgroundIt(Runnable r, String description) {

        if (busyText != null) {
            Toolkit.getDefaultToolkit().beep();

            return;
        }

        // set Waiting mode here.  Inverse op must be called by final()
        // in the Thread.run() of every background thread.
        setWaiting(description);
        SwingUtilities.invokeLater(r);
    }
