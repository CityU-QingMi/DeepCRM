        @Override
        public Set<String> addMapping(String... urlPatterns)
        {
            illegalStateIfContextStarted();
            Set<String> clash=null;
            for (String pattern : urlPatterns)
            {
                ServletMapping mapping = _servletHandler.getServletMapping(pattern);
                if (mapping!=null)
                {
                    //if the servlet mapping was from a default descriptor, then allow it to be overridden
                    if (!mapping.isDefault())
                    {
                        if (clash==null)
                            clash=new HashSet<String>();
                        clash.add(pattern);
                    }
                }
            }

            //if there were any clashes amongst the urls, return them
            if (clash!=null)
                return clash;

            //otherwise apply all of them
            ServletMapping mapping = new ServletMapping(Source.JAVAX_API);
            mapping.setServletName(ServletHolder.this.getName());
            mapping.setPathSpecs(urlPatterns);
            _servletHandler.addServletMapping(mapping);

            return Collections.emptySet();
        }
