    private void writeUser(File usersFile)
        throws Exception
    {
        try (Writer writer = new BufferedWriter(new FileWriter(usersFile)))
        {
            writer.append("tom: tom, roleA\n");
            writer.append("dick: dick, roleB\n");
            writer.append("harry: harry, roleA, roleB\n");
        }
    }
