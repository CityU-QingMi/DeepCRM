        public void visit(Node.CustomTag n) throws JasperException {
            TagFileInfo tagFileInfo = n.getTagFileInfo();
            if (tagFileInfo != null) {
                String tagFilePath = tagFileInfo.getPath();
                if (tagFilePath.startsWith("/META-INF/")) {
                    // For tags in JARs, add the TLD and the tag as a dependency
                    String[] location =
                        compiler.getCompilationContext().getTldLocation(
                            tagFileInfo.getTagInfo().getTagLibrary().getURI());
                    // Add TLD
                    pageInfo.addDependant("jar:" + location[0] + "!/" +
                            location[1]);
                    // Add Tag
                    pageInfo.addDependant("jar:" + location[0] + "!" +
                            tagFilePath);
                } else {
                    pageInfo.addDependant(tagFilePath);
                }
                Class c = loadTagFile(compiler, tagFilePath, n.getTagInfo(),
                        pageInfo);
                n.setTagHandlerClass(c);
            }
            visitBody(n);
        }
