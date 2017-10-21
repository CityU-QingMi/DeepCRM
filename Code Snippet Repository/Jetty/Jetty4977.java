        public Config asSubConfig(Path dir)
        {
            Config subconfig = new Config(dir,this);
            if (dir == this.path)
                throw new IllegalStateException("sub "+dir.toString()+" of "+this);

            if (this.recurseDepth == UNLIMITED_DEPTH)
                subconfig.recurseDepth = UNLIMITED_DEPTH;
            else
                subconfig.recurseDepth = this.recurseDepth - (dir.getNameCount() - this.path.getNameCount());                            
            
            if (LOG.isDebugEnabled())
                LOG.debug("subconfig {} of {}",subconfig,path);
            return subconfig;
        }
