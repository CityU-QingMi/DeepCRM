        public ClassifiedOverlay(String name, File origin) throws IOException
        {
            super(name,origin);
            
            int l=1;
            int e=name.indexOf('=');
            if (e<0)
            {
                l=2;
                e=name.indexOf("--");
            }
            _templateName=e>=0?name.substring(0,e):name;
            _classifier=e>=0?name.substring(e+l):null;
        }
