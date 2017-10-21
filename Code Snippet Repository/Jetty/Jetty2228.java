        public void filesChanged(List<String> filenames) throws Exception
        {
            __log.debug("Changed {}",filenames);
            
            Set<String> changes = new HashSet<String>();
            for (String filename:filenames)
            {
                
                File file=new File(filename);
                if (file.getName().startsWith(".") || file.getName().endsWith(".swp"))
                    continue;
                
                String relname=file.toURI().getPath().substring(_scanDirURI.length());
                                
                File rel = new File(relname);
                
                String dir=null;
                String name=null;
                String parent=rel.getParent();
                while (parent!=null)
                {
                    name=rel.getName();
                    dir=parent;
                    rel=rel.getParentFile();
                    parent=rel.getParent();
                }
                
                String uri=dir+"/"+name;

                for (Pattern p : __scanPatterns)
                {
                    if (p.matcher(relname).matches())
                    {
                        __log.debug("{} == {}",relname,p.pattern());
                        changes.add(uri);
                    }
                    else
                        __log.debug("{} != {}",relname,p.pattern());
                }
            }
            
            if (changes.size()>0)
                OverlayedAppProvider.this.updateLayers(changes);
        }
