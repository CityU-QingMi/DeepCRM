    public static File getFile (String sessionId)
    {
        assertNotNull(_tmpDir);
        assertTrue(_tmpDir.exists());
        String[] files = _tmpDir.list();
        assertNotNull(files);
        String fname = null;
        for (String name:files)
        {
            if (name.contains(sessionId))
            {
                fname=name;
                break;
            }
        }
        if (fname != null)
            return new File (_tmpDir, fname);
        return null;
    }
