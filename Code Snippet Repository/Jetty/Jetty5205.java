    @Override
    public void copyTo(File destination) throws IOException
    {
        if (isDirectory())
        {
            IO.copyDir(this.path.toFile(),destination);
        }
        else
        {
            Files.copy(this.path,destination.toPath());
        }
    }
