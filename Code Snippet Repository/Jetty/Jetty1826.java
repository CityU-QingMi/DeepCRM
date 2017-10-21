        public void putAll(Map map)
        {
            if (map != null)
            {
                for (Object o: map.entrySet())
                {
                    Map.Entry entry = (Entry) o;
                    put(entry.getKey(), entry.getValue());
                }
            }
        }
