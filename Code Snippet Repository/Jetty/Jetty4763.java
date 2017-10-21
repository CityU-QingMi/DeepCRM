        public boolean put(String s, V v)
        {
            boolean added = _trie.put(s,v);
            while (!added && _growby>0)
            {
                ArrayTernaryTrie<V> bigger = new ArrayTernaryTrie<>(_trie._key.length+_growby);
                for (Map.Entry<String,V> entry : _trie.entrySet())
                    bigger.put(entry.getKey(),entry.getValue());
                _trie = bigger;
                added = _trie.put(s,v);
            }
            
            return added;
        }
