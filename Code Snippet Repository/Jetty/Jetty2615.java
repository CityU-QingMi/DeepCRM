        public void addJars (Resource lib) throws IOException
        {
            if (lib == null || !lib.exists())
                throw new IllegalStateException ("No such lib: "+lib);

            String[] list = lib.list();
            if (list==null)
                return;

            for (String path : list)
            {
                if (".".equals(path) || "..".equals(path))
                    continue;

                try(Resource item = lib.addPath(path))
                {
                    if (item.isDirectory())
                        addJars(item);
                    else
                    {
                        String lowerCasePath = path.toLowerCase(Locale.ENGLISH);
                        if (lowerCasePath.endsWith(".jar") ||
                            lowerCasePath.endsWith(".zip"))
                        {
                            URL url = item.getURL();
                            _classpath.add(url);
                        }
                    }
                }
            }
        }
