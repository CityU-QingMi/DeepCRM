    private void startScanners() throws Exception
    {
        for (AntWebAppContext awc:webApplications)
        {
            if (scanIntervalSecs <= 0)
                return;

            List<File> scanList = awc.getScanFiles();
 
            TaskLog.log("Web application '" + awc + "': starting scanner at interval of "
                    + scanIntervalSecs + " seconds.");
            Scanner.Listener changeListener = new WebAppScannerListener(awc);
            Scanner scanner = new Scanner();
            scanner.setScanInterval(scanIntervalSecs);
            scanner.addListener(changeListener);
            scanner.setScanDirs(scanList);
            scanner.setReportExistingFilesOnStartup(false);
            scanner.start();
        }  
    }
