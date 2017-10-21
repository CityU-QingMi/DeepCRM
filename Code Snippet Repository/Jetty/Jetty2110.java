        public Void call() throws Exception
        {
            if (_parser != null)
            {
                org.eclipse.jetty.osgi.annotations.AnnotationParser osgiAnnotationParser = (org.eclipse.jetty.osgi.annotations.AnnotationParser)_parser;
                Bundle bundle = osgiAnnotationParser.getBundle(_resource);
                if (_stat != null)
                    _stat.start();
                osgiAnnotationParser.parse(_handlers, bundle); 
                if (_stat != null)
                    _stat.end();
            }
            return null;
        }
