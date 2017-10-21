    private void deleteFile(File file) throws IOException
    {
        if (OS.IS_WINDOWS)
        {
            // Windows doesn't seem to like to delete content that was recently created
            // Attempt a delete and if it fails, attempt a rename
            boolean deleted = file.delete();
            if (!deleted)
            {
                File deletedDir = MavenTestingUtils.getTargetFile(".deleted");
                FS.ensureDirExists(deletedDir);
                File dest = File.createTempFile(file.getName(), "deleted", deletedDir);
                boolean renamed = file.renameTo(dest);
                if (!renamed)
                    System.err.println("WARNING: unable to move file out of the way: " + file.getName());
            }
        }
        else
        {
            Assert.assertTrue("Deleting: " + file.getName(), file.delete());
        }
    }
