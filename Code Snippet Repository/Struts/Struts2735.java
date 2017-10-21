        public GenerateVisitor(boolean isTagFile, ServletWriter out,
                ArrayList methodsBuffered,
                FragmentHelperClass fragmentHelperClass, ClassLoader loader,
                TagInfo tagInfo) {

            this.isTagFile = isTagFile;
            this.out = out;
            this.methodsBuffered = methodsBuffered;
            this.fragmentHelperClass = fragmentHelperClass;
            this.loader = loader;
            this.tagInfo = tagInfo;
            methodNesting = 0;
            handlerInfos = new Hashtable();
            tagVarNumbers = new Hashtable();
            textMap = new HashMap();
        }
