    public static InputStream substitueManifest(final Manifest newmanifest,
            final InputStream rawIn) throws IOException
    {
        final PipedOutputStream pOut = new PipedOutputStream();
        PipedInputStream pIn = new PipedInputStream(pOut);
        Runnable run = new Runnable()
        {
            public void run()
            {
                JarInputStream jin = null;
                JarOutputStream dest = null;
                try
                {
                    jin = new JarInputStream(rawIn, false);
                    dest = new JarOutputStream(pOut, newmanifest);
                    ZipEntry next = jin.getNextEntry();
                    while (next != null)
                    {
                        if (next.getName().equalsIgnoreCase(JarFile.MANIFEST_NAME))
                        {
                            continue;
                        }
                        dest.putNextEntry(next);
                        if (next.getSize() > 0)
                        {
                            IO.copy(jin,dest,next.getSize());
                        }
                        next = jin.getNextJarEntry();
                    }
                }
                catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                finally
                {
                    if (dest != null) IO.close(dest);
                    if (jin != null) IO.close(jin);
                    IO.close(pOut);
                }
            }
        };
        Thread th = new Thread(run);
        th.start();
        return pIn;
    }
