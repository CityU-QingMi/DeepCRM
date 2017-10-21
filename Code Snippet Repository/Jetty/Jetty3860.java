        public Collection<String> getServletNameMappings()
        {
            FilterMapping[] mappings =_servletHandler.getFilterMappings();
            List<String> names=new ArrayList<String>();
            for (FilterMapping mapping : mappings)
            {
                if (mapping.getFilterHolder()!=FilterHolder.this)
                    continue;
                String[] servlets=mapping.getServletNames();
                if (servlets!=null && servlets.length>0)
                    names.addAll(Arrays.asList(servlets));
            }
            return names;
        }
