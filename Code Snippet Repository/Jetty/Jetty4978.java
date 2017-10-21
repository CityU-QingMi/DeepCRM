        public boolean test(Path path)
        {
            if (excludeHidden && isHidden(path))
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("test({}) -> [Hidden]", toShortPath(path));
                return false;
            }

            if (!path.startsWith(this.path))
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("test({}) -> [!child {}]", toShortPath(path), this.path);
                return false;
            }

            if (recurseDepth!=UNLIMITED_DEPTH)
            {
                int depth = path.getNameCount() - this.path.getNameCount() - 1;

                if (depth>recurseDepth)
                {
                    if (LOG.isDebugEnabled())
                        LOG.debug("test({}) -> [depth {}>{}]",toShortPath(path),depth,recurseDepth);
                    return false;
                }
            }

            boolean matched = includeExclude.test(path);

            if (LOG.isDebugEnabled())
                LOG.debug("test({}) -> {}", toShortPath(path), matched);

            return matched;
        }
