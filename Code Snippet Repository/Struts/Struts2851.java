        public TagAttributeInfo getTagAttributeInfo(String name) {
            TagInfo info = this.getTagInfo();
            if (info == null)
                return null;
            TagAttributeInfo[] tai = info.getAttributes();
            for (int i = 0; i < tai.length; i++) {
                if (tai[i].getName().equals(name)) {
                    return tai[i];
                }
            }
            return null;
        }
