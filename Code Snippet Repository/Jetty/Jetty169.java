    public boolean isIncluded(String pathToFile)
    {
        Iterator scanners = directoryScanners.iterator();
        while (scanners.hasNext())
        {
            DirectoryScanner scanner = (DirectoryScanner) scanners.next();
            scanner.scan();
            String[] includedFiles = scanner.getIncludedFiles();

            for (int i = 0; i < includedFiles.length; i++)
            {
                File includedFile = new File(scanner.getBasedir(), includedFiles[i]);
                if (pathToFile.equalsIgnoreCase(includedFile.getAbsolutePath()))
                {
                    return true;
                }
            }
        }

        return false;
    }
