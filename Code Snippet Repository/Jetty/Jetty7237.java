        public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException
        {
            String name = null;
            if (_parser.isNamespaceAware())
                name = localName;

            if (name == null || "".equals(name))
                name = qName;

            Node node = new Node(_context, name, attrs);


            // check if the node matches any xpaths set?
            if (_xpaths != null)
            {
                String path = node.getPath();
                boolean match = false;
                for (int i = LazyList.size(_xpaths); !match && i-- > 0;)
                {
                    String xpath = (String) LazyList.get(_xpaths, i);

                    match = path.equals(xpath) || xpath.startsWith(path) && xpath.length() > path.length() && xpath.charAt(path.length()) == '/';
                }

                if (match)
                {
                    _context.add(node);
                    _context = node;
                }
                else
                {
                    _parser.getXMLReader().setContentHandler(_noop);
                }
            }
            else
            {
                _context.add(node);
                _context = node;
            }

            ContentHandler observer = null;
            if (_observerMap != null)
                observer = (ContentHandler) _observerMap.get(name);
            _observers.push(observer);

            for (int i = 0; i < _observers.size(); i++)
                if (_observers.get(i) != null)
                    ((ContentHandler) _observers.get(i)).startElement(uri, localName, qName, attrs);
        }
