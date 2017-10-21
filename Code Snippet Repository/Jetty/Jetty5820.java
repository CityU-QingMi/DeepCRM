        public UseCases(String ref) throws Exception {
            this.data = new ArrayList<Data[]>();
            // relative directory reference
            this.relRef = OS.separators(ref);
            // File object reference
            this.fileRef = MavenTestingUtils.getProjectDir(relRef);
            // URI reference
            this.uriRef = fileRef.toURI();
            
            // create baseline cases
            baseCases = new Data[] { 
                new Data(relRef,EXISTS,DIR), 
                new Data(uriRef,EXISTS,DIR), 
                new Data(fileRef,EXISTS,DIR) 
            };
            
            // add all baseline cases
            for (Data bcase : baseCases)
            {
                addCase(bcase);
            }
        }
