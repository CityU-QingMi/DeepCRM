    public List getBaseDirectories()
    {
        List baseDirs = new ArrayList();
        Iterator scanners = directoryScanners.iterator();
        while (scanners.hasNext())
        {
            DirectoryScanner scanner = (DirectoryScanner) scanners.next();
            baseDirs.add(scanner.getBasedir());
        }

        return baseDirs;
    }
