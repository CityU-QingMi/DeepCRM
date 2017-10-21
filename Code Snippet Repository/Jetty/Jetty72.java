        public ServletContainerInitializerOrdering (String ordering)
        {
            if (ordering != null)
            {
                _ordering = ordering;
                
                String[] tmp = StringUtil.csvSplit(ordering);
                
                for (int i=0; i<tmp.length; i++)
                {
                    String s = tmp[i].trim();
                    _indexMap.put(s, Integer.valueOf(i));
                    if ("*".equals(s))
                    {
                        if (_star != null)
                            throw new IllegalArgumentException("Duplicate wildcards in ServletContainerInitializer ordering "+ordering);
                        _star = Integer.valueOf(i);
                    }
                    
                }
            }
        }
