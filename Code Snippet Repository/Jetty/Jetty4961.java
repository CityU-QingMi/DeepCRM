        public Config(Path path, Config parent)
        {
            this.parent = parent;
            this.includeExclude = parent==null ? new IncludeExcludeSet<>(PathMatcherSet.class) : parent.includeExclude;
            
            Path dir = path;
            if (!Files.exists(path))
                throw new IllegalStateException("Path does not exist: "+path);
            
            if (!Files.isDirectory(path))
            {
                dir = path.getParent();
                includeExclude.include(new ExactPathMatcher(path));
                setRecurseDepth(0);
            }
            
            this.path = dir;
        }
