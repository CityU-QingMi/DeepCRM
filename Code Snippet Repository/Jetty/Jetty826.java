    private void deleteContents(File dir)
    {
        // System.err.printf("Delete  (dir) %s/%n",dir);
        if (!dir.exists())
        {
            return;
        }

        File[] files = dir.listFiles();
        if (files != null)
        {
            for (File file : files)
            {
                // Safety measure. only recursively delete within target directory.
                if (file.isDirectory() && file.getAbsolutePath().contains("target" + File.separator))
                {
                    deleteContents(file);
                    Assert.assertTrue("Delete failed: " + file.getAbsolutePath(),file.delete());
                }
                else
                {
                    System.err.printf("Delete (file) %s%n",file);
                    Assert.assertTrue("Delete failed: " + file.getAbsolutePath(),file.delete());
                }
            }
        }
    }
