    public void startScanner() throws Exception
    {
        if (!isScanningEnabled())
            return;

        scanner.setNotifyExistingOnStart(false);
       
       
        scanner.start();
    }
