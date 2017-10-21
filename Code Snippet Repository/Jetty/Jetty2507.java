    protected String normalizePath(Path path)
    {
        for (PathAttribute a : paths)
        {
            try
            {
                if (path.equals(a.path) || Files.isSameFile(path,a.path))
                    return String.format("${%s}",a.key);
            }
            catch (IOException ignore)
            {
                LOG.ignore(ignore);
            }

            if (path.startsWith(a.path))
                return String.format("${%s}%c%s",a.key,File.separatorChar,a.path.relativize(path).toString());
        }

        return path.toString();
    }
