    public boolean copyDirectory(Path source, Path destination) throws IOException
    {
        boolean modified=false;
        
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(source))
        {
            for (Path from : stream)
            {
                Path to = destination.resolve(from.getFileName());
                if (Files.isDirectory(from))
                {
                    if (FS.ensureDirectoryExists(to))
                    {
                        StartLog.log("MKDIR",_basehome.toShortForm(to));
                        modified = true;
                    }
                    
                    if (copyDirectory(from,to))
                        modified = true;
                }
                else if (!Files.exists(to))
                {
                    StartLog.log("COPY ","%s to %s",_basehome.toShortForm(from),_basehome.toShortForm(to));
                    Files.copy(from,to);
                    modified = true;
                }
            }   
        }
        
        return modified;
    }
