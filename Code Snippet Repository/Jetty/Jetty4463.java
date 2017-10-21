    public String toShortForm(final Path path)
    {
        Path apath = path.toAbsolutePath();

        for (ConfigSource source : sources)
        {
            if (source instanceof DirConfigSource)
            {
                DirConfigSource dirsource = (DirConfigSource)source;
                Path dir = dirsource.getDir();
                if (apath.startsWith(dir))
                {
                    if (dirsource.isPropertyBased())
                    {
                        Path relative = dir.relativize(apath);
                        return String.format("%s%c%s",dirsource.getId(),File.separatorChar,relative.toString());
                    }
                    else
                    {
                        return apath.toString();
                    }
                }
            }
        }

        return apath.toString();
    }
