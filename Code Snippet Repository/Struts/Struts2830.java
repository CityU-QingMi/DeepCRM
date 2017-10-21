    public static String getTagHandlerClassName(String path, String urn,
                                                ErrorDispatcher err) throws JasperException {

        String className = null;
        int begin = 0;
        int index;

        index = path.lastIndexOf(".tag");
        if (index == -1) {
            err.jspError("jsp.error.tagfile.badSuffix", path);
        }

        //It's tempting to remove the ".tag" suffix here, but we can't.
        //If we remove it, the fully-qualified class name of this tag
        //could conflict with the package name of other tags.
        //For instance, the tag file
        //    /WEB-INF/tags/foo.tag
        //would have fully-qualified class name
        //    org.apache.jsp.tag.web.foo
        //which would conflict with the package name of the tag file
        //    /WEB-INF/tags/foo/bar.tag

        index = path.indexOf(WEB_INF_TAGS);
        if (index != -1) {
            className = "org.apache.jsp.tag.web.";
            begin = index + WEB_INF_TAGS.length();
        } else {
            index = path.indexOf(META_INF_TAGS);
            if (index != -1) {
                className = getClassNameBase(urn);
                begin = index + META_INF_TAGS.length();
            } else {
                err.jspError("jsp.error.tagfile.illegalPath", path);
            }
        }

        className += makeJavaPackage(path.substring(begin));

        return className;
    }
