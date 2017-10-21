        @Override
        public Map<String, List<String>> getParameterMap()
        {
            Map<String,List<String>> paramMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
            
            String query = getQueryString();
            MultiMap<String> multimap = new MultiMap<>();
            UrlEncoded.decodeTo(query,multimap,StandardCharsets.UTF_8);
            
            paramMap.putAll(multimap);
                    
            return paramMap;
        }
