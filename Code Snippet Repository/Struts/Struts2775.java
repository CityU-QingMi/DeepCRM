    public TagFileInfo getTagFile(String shortName) {

        TagFileInfo tagFile = super.getTagFile(shortName);
        if (tagFile == null) {
            String path = (String) tagFileMap.get(shortName);
            if (path == null) {
                return null;
            }

            TagInfo tagInfo = null;
            try {
                tagInfo = TagFileProcessor.parseTagFileDirectives(pc,
                        shortName,
                        path,
                        pc.getJspCompilationContext().getTagFileJarUrl(path),
                        this);
            } catch (JasperException je) {
                throw new RuntimeException(je.toString(), je);
            }

            tagFile = new TagFileInfo(shortName, path, tagInfo);
            vec.addElement(tagFile);

            this.tagFiles = new TagFileInfo[vec.size()];
            vec.copyInto(this.tagFiles);
        }

        return tagFile;
    }
