        @Override
        public Collection<String> getMappings()
        {
            ServletMapping[] mappings =_servletHandler.getServletMappings();
            List<String> patterns=new ArrayList<String>();
            if (mappings!=null)
            {
                for (ServletMapping mapping : mappings)
                {
                    if (!mapping.getServletName().equals(getName()))
                        continue;
                    String[] specs=mapping.getPathSpecs();
                    if (specs!=null && specs.length>0)
                        patterns.addAll(Arrays.asList(specs));
                }
            }
            return patterns;
        }
