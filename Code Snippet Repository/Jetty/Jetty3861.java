        public Collection<String> getUrlPatternMappings()
        {
            FilterMapping[] mappings =_servletHandler.getFilterMappings();
            List<String> patterns=new ArrayList<String>();
            for (FilterMapping mapping : mappings)
            {
                if (mapping.getFilterHolder()!=FilterHolder.this)
                    continue;
                String[] specs=mapping.getPathSpecs();
                patterns.addAll(TypeUtil.asList(specs));
            }
            return patterns;
        }
