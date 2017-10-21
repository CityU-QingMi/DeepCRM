        DirAction handleDir(Path path)
        {
            try
            {
                if (!Files.isDirectory(path))
                    return DirAction.IGNORE;
                if (excludeHidden && isHidden(path))
                    return DirAction.IGNORE;
                if (getRecurseDepth()==0)
                    return DirAction.WATCH;
                return DirAction.ENTER;
            }
            catch(Exception e)
            {
                LOG.ignore(e);
                return DirAction.IGNORE;
            }
        }
