    public Path getPath(final String path)
    {
        Path apath = FS.toPath(path);

        if (apath.isAbsolute())
        {
            if (FS.exists(apath))
            {
                return apath;
            }
        }

        for (ConfigSource source : sources)
        {
            if (source instanceof DirConfigSource)
            {
                DirConfigSource dirsource = (DirConfigSource)source;
                Path file = dirsource.getDir().resolve(apath);
                if (FS.exists(file))
                {
                    return file;
                }
            }
        }

        // Finally, as an anonymous path
        return FS.toPath(path);
    }
