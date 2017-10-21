        public Node get(String tag)
        {
            if (_list != null)
            {
                for (int i = 0; i < _list.size(); i++)
                {
                    Object o = _list.get(i);
                    if (o instanceof Node)
                    {
                        Node n = (Node) o;
                        if (tag.equals(n._tag))
                            return n;
                    }
                }
            }
            return null;
        }
