        public Layer(String name, File origin)
            throws IOException
        {
            super();
            _name = name;
            _origin = origin;
            
            Resource resource = Resource.newResource(origin.toURI());
            
            if (resource.isDirectory())
            {
                if (_copydir)
                {
                    File tmp=tmpdir(name,"extract");
                    __log.info("Extract {} to {}",origin,tmp);
                    IO.copyDir(origin,tmp);
                    _resourceBase=Resource.newResource(tmp.toURI());
                    _resourceBaseIsCopy=true;
                }
                else
                {
                    _resourceBase=resource;
                    _resourceBaseIsCopy=false;
                }
            }
            else 
            {
                Resource jar = JarResource.newJarResource(resource);
                File tmp=tmpdir(name,"extract");
                __log.info("Extract {} to {}",jar,tmp);
                jar.copyTo(tmp);
                _resourceBase=Resource.newResource(tmp.toURI());
                _resourceBaseIsCopy=true;
            }    
        }
