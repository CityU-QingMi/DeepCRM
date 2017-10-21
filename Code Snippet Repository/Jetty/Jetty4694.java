    private boolean processModFile(Path path) throws IOException
    {
        List<String> lines = readLines(path);
        if (processFileRefs(lines))
        {
            // the lines are now dirty, save them.
            System.out.printf("Updating: %s%n",path);
            saveLines(path,lines);
            return true;
        }

        // no update performed
        return false;
    }
